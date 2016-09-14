package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.ifopendoors.entity.Login;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;

public class LoginDAO extends AbstractDAO<Integer, Login>{

	private static Logger logger = LogManager.getLogger(LoginDAO.class);

	private static LoginDAO instance;
	
	public static LoginDAO getInstance() {
		instance = new LoginDAO();
		return instance;
	}
	
	@Override
	public List<Login> getAll() throws SQLExceptionIFOpenDoors {
		return super.getAll("Login.getAll");
	}
	
	@Override
	public int delete(Integer pk) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Login.class;
	}	
}
