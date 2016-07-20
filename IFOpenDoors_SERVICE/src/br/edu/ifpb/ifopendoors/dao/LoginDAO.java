package br.edu.ifpb.ifopendoors.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.ifopendoors.entity.Login;
import br.edu.ifpb.ifopendoors.entity.Person;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.hibernate.HibernateUtil;

public class LoginDAO extends AbstractDAO<Integer, Login>{

	private static Logger logger = LogManager.getLogger(LoginDAO.class);

	private static LoginDAO instance;
	
	public static LoginDAO getInstance() {
		instance = new LoginDAO();
		return instance;
	}

	@Override
	public Login getById(Integer idLogin) throws SQLExceptionIFOpenDoors {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Login login = null;
		
		try {
		
			session.beginTransaction();
			login = (Login) session.get(Login.class, idLogin);
	        Hibernate.initialize(login);
	        session.getTransaction().commit();
	        
		} catch (HibernateException e) {
			
			logger.error(e.getMessage());
			session.getTransaction().rollback();
			
		} finally {
			
			session.close();
		}
		
		return login;
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
}
