package br.edu.ifpb.ifopendoors.controller;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.ifopendoors.dao.CloseDAO;
import br.edu.ifpb.ifopendoors.dao.DoorDAO;
import br.edu.ifpb.ifopendoors.dao.OpenDAO;
import br.edu.ifpb.ifopendoors.dao.PersonDAO;
import br.edu.ifpb.ifopendoors.dao.RoomDAO;
import br.edu.ifpb.ifopendoors.dao.TypeRoomDAO;
import br.edu.ifpb.ifopendoors.entity.Close;
import br.edu.ifpb.ifopendoors.entity.Door;
import br.edu.ifpb.ifopendoors.entity.Erro;
import br.edu.ifpb.ifopendoors.entity.Open;
import br.edu.ifpb.ifopendoors.entity.Person;
import br.edu.ifpb.ifopendoors.entity.Room;
import br.edu.ifpb.ifopendoors.entity.TypeRoom;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.util.BancoUtil;
import br.edu.ifpb.ifopendoors.validatio.Validate;

@Path("gate")
public class GateController {

	@POST
	@Path("/insert")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insertRoom(Room room) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());
		
		try {
			
			Door door = DoorDAO.getInstance().getById(room.getDoor().getId());
			TypeRoom typeRoom = TypeRoomDAO.getInstance().getById(room.getDoor().getId());
			
			room.setDoor(door);
			room.setTipoSala(typeRoom);
			
			Integer idRoom = RoomDAO.getInstance().insert(room);
			
			if (idRoom != BancoUtil.IDVAZIO) {

				builder.status(Response.Status.OK);
				builder.entity(room);
			}
		
		} catch (SQLExceptionIFOpenDoors qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
			
		}		
		
		return builder.build();		
	}
	
	@POST
	@Path("/enable")
	@Consumes("application/json")
	@Produces("application/json")
	public Response enable(Open open) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());
		
		// Validação dos dados de entrada.
		int validacao = Validate.open(open);
		
		
		if (validacao == Validate.VALIDATE_OK) {
			
			// Consultar Room e Person.
			Integer idPerson = open.getPerson().getId();
			Person person = PersonDAO.getInstance().getById(idPerson);	

			Integer idRoom = open.getRoom().getId();
			Room room = RoomDAO.getInstance().getById(idRoom);
			
			Integer idDoor = room.getDoor().getId();
			Door door = DoorDAO.getInstance().getById(idDoor);
			
			// Verificar disponibilidade de sala.
			//OpenDAO.getInstance().isOpenRoom(idRoom);
			
			if (person != null && room != null && door.getIp() != null) {
				
				open.setPerson(person);
				room.setDoor(door);
				open.setRoom(room);
				
				Date now = new Date();
				open.setTime(now);
				
				Integer idOpen = OpenDAO.getInstance().insert(open);
				
				if (idOpen != BancoUtil.IDVAZIO) {
					
					Client client = ClientBuilder.newClient();
		    		Response response = client.target("http://" + open.getRoom().getDoor().getIp() + "/open")
		    				.request().get();
				}
			}
		}
		
		try {
			Thread.sleep(20000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Close close = new Close();
		close.setOpen(open);
		
		Date now = new Date();
		close.setTime(now);
		
		Integer idClose = CloseDAO.getInstance().insert(close);
		
		if (idClose != BancoUtil.IDVAZIO) {
			
			Client client = ClientBuilder.newClient();
			Response response = client.target("http://" + open.getRoom().getDoor().getIp() + "/close")
    				.request().get();
			
			builder.status(Response.Status.OK);
			builder.entity(close);
		}
		
		return builder.build();		
	}
}
