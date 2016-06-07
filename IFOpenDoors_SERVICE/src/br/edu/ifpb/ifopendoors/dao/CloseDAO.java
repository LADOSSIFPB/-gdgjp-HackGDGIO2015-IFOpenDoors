package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.edu.ifpb.ifopendoors.entity.Close;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.hibernate.HibernateUtil;

public class CloseDAO extends AbstractDAO<Integer, Close>{

	private static Logger logger = LogManager.getLogger(CloseDAO.class);

	private static CloseDAO instance;
	
	public static CloseDAO getInstance() {
		instance = new CloseDAO();
		return instance;
	}

	@Override
	public Close getById(Integer idClose) throws SQLExceptionIFOpenDoors {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Close close = null;
		
		try {
		
			session.beginTransaction();
			close = (Close) session.get(Close.class, idClose);
	        Hibernate.initialize(close);
	        session.getTransaction().commit();
	        
		} catch (HibernateException e) {
			
			logger.error(e.getMessage());
			session.getTransaction().rollback();
			
		} finally {
			
			session.close();
		}
		
		return close;
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
}
