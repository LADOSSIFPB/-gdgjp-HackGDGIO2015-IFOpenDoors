package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.ifopendoors.entity.Open;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;

public class OpenDAO extends AbstractDAO<Integer, Open> {

	private static Logger logger = LogManager.getLogger(OpenDAO.class);

	private static OpenDAO instance;
	
	public static OpenDAO getInstance() {
		instance = new OpenDAO();
		return instance;
	}

	@Override
	public int delete(Integer pk) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Open> getAll() throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Open getById(Integer pk) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return null;
	}
}
