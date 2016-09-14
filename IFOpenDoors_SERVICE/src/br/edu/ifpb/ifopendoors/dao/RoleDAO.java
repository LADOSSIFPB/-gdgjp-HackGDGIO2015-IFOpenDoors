package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.edu.ifpb.ifopendoors.entity.Door;
import br.edu.ifpb.ifopendoors.entity.Role;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.hibernate.HibernateUtil;

public class RoleDAO extends AbstractDAO<Integer, Role>{

	private static Logger logger = LogManager.getLogger(RoleDAO.class);

	private static RoleDAO instance;
	
	public static RoleDAO getInstance() {
		instance = new RoleDAO();
		return instance;
	}

	@Override
	public List<Role> getAll() throws SQLExceptionIFOpenDoors {
		return super.getAll("Role.getAll");
	}
	
	@Override
	public int delete(Integer pk) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Role.class;
	}	
}
