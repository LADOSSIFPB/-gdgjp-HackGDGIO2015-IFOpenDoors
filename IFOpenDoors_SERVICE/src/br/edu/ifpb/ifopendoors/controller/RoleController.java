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

import br.edu.ifpb.ifopendoors.dao.DoorDAO;
import br.edu.ifpb.ifopendoors.dao.OpenDAO;
import br.edu.ifpb.ifopendoors.dao.PersonDAO;
import br.edu.ifpb.ifopendoors.dao.RoleDAO;
import br.edu.ifpb.ifopendoors.dao.RoomDAO;
import br.edu.ifpb.ifopendoors.dao.TypeRoomDAO;
import br.edu.ifpb.ifopendoors.entity.Door;
import br.edu.ifpb.ifopendoors.entity.Erro;
import br.edu.ifpb.ifopendoors.entity.Open;
import br.edu.ifpb.ifopendoors.entity.Person;
import br.edu.ifpb.ifopendoors.entity.Role;
import br.edu.ifpb.ifopendoors.entity.Room;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.util.BancoUtil;
import br.edu.ifpb.ifopendoors.validatio.Validate;

@Path("role")
public class RoleController {

	@POST
	@Path("/insert")
	@Consumes("application/json")
	@Produces("application/json")
	public Response open(Role role) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());
		
		try {			
			
			Integer idRole = RoleDAO.getInstance().insert(role);
			
			if (idRole != BancoUtil.IDVAZIO) {

				builder.status(Response.Status.OK);
				builder.entity(role);
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
	@Path("/all")
	@Produces("application/json")
	public List<Role> getAll() {
		
		List<Role> role = new ArrayList<Role>();
		
		role = RoleDAO.getInstance().getAll();
		
		return role;
	}
	
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getRoleById(@PathParam("id") int idRole) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Role role = RoleDAO.getInstance().getById(idRole); 
			
			builder.status(Response.Status.OK);
			builder.entity(role);

		} catch (SQLExceptionIFOpenDoors qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}
}
