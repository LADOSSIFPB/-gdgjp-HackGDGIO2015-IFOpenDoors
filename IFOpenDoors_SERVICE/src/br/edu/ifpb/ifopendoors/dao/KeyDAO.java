package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.ifopendoors.entity.Key;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;

public class KeyDAO extends AbstractDAO<Integer, Key>{
	
	private static Logger logger = LogManager.getLogger(KeyDAO.class);

	private static KeyDAO instance;
	
	public static KeyDAO getInstance() {
		instance = new KeyDAO();
		return instance;
	}

	@Override
	public int delete(Integer pk) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Key> getAll() throws SQLExceptionIFOpenDoors {
		return super.getAll("Key.getAll");
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Key.class;
	}
}
