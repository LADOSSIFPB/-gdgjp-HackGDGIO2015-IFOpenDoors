package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.ifopendoors.entity.Close;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;

public class CloseDAO extends AbstractDAO<Integer, Close>{

	private static Logger logger = LogManager.getLogger(CloseDAO.class);

	private static CloseDAO instance;
	
	public static CloseDAO getInstance() {
		instance = new CloseDAO();
		return instance;
	}

	@Override
	public List<Close> getAll() throws SQLExceptionIFOpenDoors {
		return super.getAll("Close.getAll");
	}
	
	@Override
	public int delete(Integer pk) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Close.class;
	}	
}
