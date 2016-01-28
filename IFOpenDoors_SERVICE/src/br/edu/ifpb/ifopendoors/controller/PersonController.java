package br.edu.ifpb.ifopendoors.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.ifopendoors.dao.PersonDAO;
import br.edu.ifpb.ifopendoors.entity.Erro;
import br.edu.ifpb.ifopendoors.entity.Person;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.util.BancoUtil;

/**
 * Controlador para Person.
 * 
 * @author Rhavy Maia
 * @version 1.0
 */
@Path("person")
public class PersonController {

	private static Logger logger = LogManager.getLogger(PersonController.class);
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<Person> getAll() {
		
		List<Person> person = new ArrayList<Person>();
		
		person = PersonDAO.getInstance().getAll();
		
		return person;
	}
	
	@POST
	@Path("/insert")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insertPerson(Person person) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());
		
		try {			
			
			Integer idPerson = PersonDAO.getInstance().insert(person);
			
			if (idPerson != BancoUtil.IDVAZIO) {

				builder.status(Response.Status.OK);
				builder.entity(person);
			}
		
		} catch (SQLExceptionIFOpenDoors qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
			
		}		
		
		return builder.build();		
	}
	
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getRoomById(@PathParam("id") int idPerson) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Person person = PersonDAO.getInstance().getById(idPerson); 
			
			builder.status(Response.Status.OK);
			builder.entity(person);

		} catch (SQLExceptionIFOpenDoors qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}
}
