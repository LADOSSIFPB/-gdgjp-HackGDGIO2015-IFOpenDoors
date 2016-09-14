package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.ifopendoors.entity.TypeRoom;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;

public class TypeRoomDAO extends AbstractDAO<Integer, TypeRoom>{

	private static Logger logger = LogManager.getLogger(TypeRoomDAO.class);

	private static TypeRoomDAO instance;
	
	public static TypeRoomDAO getInstance() {
		instance = new TypeRoomDAO();
		return instance;
	}


	@Override
	public List<TypeRoom> getAll() throws SQLExceptionIFOpenDoors {
		return super.getAll("TypeRoom.getAll");
	}
	
	@Override
	public int delete(Integer pk) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return TypeRoom.class;
	}	
}
