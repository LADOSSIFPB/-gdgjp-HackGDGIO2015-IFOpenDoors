package br.edu.ifpb.ifopendoors.controller;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.ifopendoors.dao.TypeRoomDAO;
import br.edu.ifpb.ifopendoors.entity.Erro;
import br.edu.ifpb.ifopendoors.entity.TypeRoom;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.util.BancoUtil;

@Path("typeroom")
public class TypeRoomController {

	@POST
	@Path("/insert")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insertRoom(TypeRoom typeRoom) {
		System.out.println("ok");
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());
		
		try {			
			
			Integer idTypeRoom = TypeRoomDAO.getInstance().insert(typeRoom);
			
			if (idTypeRoom != BancoUtil.IDVAZIO) {

				builder.status(Response.Status.OK);
				builder.entity(typeRoom);
			}
		
		} catch (SQLExceptionIFOpenDoors qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
			
		}		
		
		return builder.build();		
	}
}
