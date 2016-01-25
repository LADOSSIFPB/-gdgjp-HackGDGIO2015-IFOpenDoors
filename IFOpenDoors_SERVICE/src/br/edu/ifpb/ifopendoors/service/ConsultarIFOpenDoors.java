package br.edu.ifpb.ifopendoors.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.ifopendoors.entity.Arduino;
import br.edu.ifpb.ifopendoors.entity.Close;
import br.edu.ifpb.ifopendoors.entity.Door;
import br.edu.ifpb.ifopendoors.entity.Open;
import br.edu.ifpb.ifopendoors.entity.Person;
import br.edu.ifpb.ifopendoors.entity.Room;
import br.edu.ifpb.ifopendoors.hibernate.HibernateUtil;

/**
 * Classe que reune serviços de consulta ao banco de dados.
 * 
 * @author Igor Barbosa
 * @author Rhavy Maia
 * @author Emanuel Guimarães
 * @author Eri Jonhson
 * @author Felipe Nascimento
 * @author Ivanildo Terceiro
 * @version 1.0
 */
@Path("door")
public class ConsultarIFOpenDoors {

	// URL e Contexto - Acesso ao Arduíno.
	private static final String APP_CONTEXT = "/door";
	
	private static final String URL_ARDUINO_SERVICE = "http://192.168.1.101:5534";
	
	private static Logger logger = LogManager.getLogger(ConsultarIFOpenDoors.class);
	
	/**
	 * Abertura da porta.
	 * 
	 * @param {'number':1}
	 * @return
	 */
	@POST
	@Path("/open")
	@Consumes("application/json")
	@Produces("application/json")
	public Response openDoor(Door door) {

		logger.info("Analisando requisição da porta");
		ResponseBuilder builder = Response.status(Response.Status.UNAUTHORIZED);
		builder.expires(new Date());
		
		// Consultar porta aberta.
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        String sql = "SELECT * "
        		+ " FROM tb_alocacao as al, tb_desalocacao as des "
        		+ " WHERE al.room_id_sala = :number"
        		+ " AND al.dt_abertura = "
        			+ " (SELECT max(al2.dt_abertura) "
        			+ " FROM tb_alocacao as al2 "
        			+ " WHERE al2.room_id_sala = :number)"
        		+ " AND al.id_alocacao = des.open_id_alocacao";
        
        Query query = session.createSQLQuery(sql)
        		.setParameter("number", door.getNumber());
        
        List result = query.list();
        
        if (!result.isEmpty()) {
        	
        	// Enviar abertura pro Arduíno
    		logger.info("Comunicação com porta(arduíno): " + door.getNumber());
    		
    		Client client = ClientBuilder.newClient();
    		Response response = client.target(URL_ARDUINO_SERVICE + APP_CONTEXT + "/open")
    				.request().post(Entity.json("{'number':" + door.getNumber() + "}"));
    		
    		int status = response.getStatus();
    		
    		if (status == HttpStatus.SC_OK) {
    			
    			logger.info("Permissão concedida - Porta aberta");   			
    			Person person = new Person();
    			person.setId(1);
    			
    			Room room = new Room();
    			room.setId(door.getNumber());
    			
    			Open open = new Open();
    			open.setPerson(person);
    			//open.setRoom(room);
    			open.setTime(new Date());
    			
    			session.save(open);  
    			
    	        session.getTransaction().commit();    			
    			
    	        door.setOpen(true);
    			door.setMensage("A porta está sendo aberta.");
    			builder.status(Response.Status.OK);    			
    		}
        } else {
			
			logger.info("Permissão negada - Porta aberta");		
			door.setOpen(false);
			door.setMensage("A porta não foi aberta.");
			builder.status(Response.Status.CONFLICT);
		}
		
		builder.entity(door);

		return builder.build();
	}

	@POST
	@Path("/closeDoor")
	@Produces("application/json")
	public Response closeDoor(Door door) {

		ResponseBuilder builder = Response.status(Response.Status.NOT_MODIFIED);
		builder.expires(new Date());

		try {
			
			// Enviar fechamento pro Arduíno
			logger.info("Comunicação com porta(arduíno): ");

			Client client = ClientBuilder.newClient();
			Response response = client.target(URL_ARDUINO_SERVICE + APP_CONTEXT + "/close").request()
					.post(Entity.json("{'number': " + door.getNumber() + "}"));

			int status = response.getStatus();

			if (status == HttpStatus.SC_OK) {
				
				// Abrindo conexão com o Banco de Dados.
				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				
				Open open = getLastOpen(door.getNumber());
				
				Close close = new Close();
				close.setOpen(open);
				close.setTime(new Date());
				
				// Registrando no BD.
		        session.save(close);		
		        session.getTransaction().commit();
		        
				// Registrando fechamento na base.
				builder.status(Response.Status.OK);
			}
			
		} catch (HibernateException e) {

			logger.info("Problema na base de dados.");
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);	
		}
		
		return builder.build();
	}
	
	private Open getLastOpen(int number) {
		
		Open open = null;
		
		try {
			
			// Abrindo conexão com o Banco de Dados.
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery("FROM tb_alocacao"
					+ " ORDER BY id_alocacao DESC");
			query.setMaxResults(1);
			
			open = (Open) query.uniqueResult();
			
		} catch (HibernateException e) {
			
			logger.info("Problema na base de dados.");
		}
		
		return open;
	}

	@GET
	@Path("/close/{idOpen}")
	@Produces("application/json")
	public Response close(
			@PathParam("idOpen") int idOpen) {		
		
		ResponseBuilder builder = Response.status(Response.Status.NOT_MODIFIED);
		builder.expires(new Date());

		try {
			
			// Abrindo conexão com o Banco de Dados.
			Session session = HibernateUtil.getSessionFactory().openSession();
	        session.beginTransaction();		
	        
	        // Porta aberta.
	        Open open = new Open();
	        open.setId(idOpen);
	        
	        // Fechando a porta.
	        Close close = new Close();
	        close.setOpen(open);
	        
	        // Registrando no BD.
	        session.save(close);		
	        session.getTransaction().commit();
			
	        // Porta fechada.
			Door door = new Door();
			door.setOpen(false);
			door.setMensage("Portão está sendo fechado.");
			builder.entity(door);

		} catch (HibernateException e) {
			
			logger.info("Problema na base de dados.");
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);			
		}
		
		return builder.build();
	}

	@POST
	@Path("/arduino")
	@Consumes("application/json")
	@Produces("application/json")
	public void receiveArduinoInformation(Arduino arduino) {
		System.out.println(arduino);
	}
	
	@GET
	@Path("/entidade")
	@Produces("application/json")
	public Response getEntity() {

		ResponseBuilder builder = Response.status(Response.Status.NOT_MODIFIED);
		builder.expires(new Date());

		Person person = new Person();
		person.setId(1);

		// Porta fechada.
		Door door = new Door();
		door.setNumber(1);
		door.setOpen(true);
		door.setPerson(person);

		Arduino arduino = new Arduino();
		
		builder.status(Response.Status.OK).entity(door);

		return builder.build();
	}
}