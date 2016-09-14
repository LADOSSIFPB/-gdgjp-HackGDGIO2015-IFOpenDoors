package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.hibernate.HibernateUtil;

public abstract class AbstractDAO<PK, T> implements GenericDAO<PK, T> {

	private static Logger logger = LogManager.getLogger(AbstractDAO.class);
	
	public abstract Class<?> getEntityClass();
	
	@Override
	public int insert(T entity) throws SQLExceptionIFOpenDoors {
		
		logger.info("Init abstract Insert to: " + entity.getClass());
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		Integer id;
		
		try {
			
			session.beginTransaction();
			id = (Integer) session.save(entity);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			
			logger.error(e.getMessage());
			session.getTransaction().rollback();
			throw e;
			
		} finally {
			
			session.close();
		}
		
		return id;
	}

	@Override
	public void update(T entity) throws SQLExceptionIFOpenDoors {
		
		logger.info("Init abstract Update to: " + entity.getClass());
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			session.beginTransaction();
			session.merge(entity);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(e.getMessage());
			session.getTransaction().rollback();

		} finally {

			session.close();
		}		
	}

	@Override
	public List<T> getAll(String namedQuery) throws SQLExceptionIFOpenDoors {
		
		logger.info("Init abstract GetAll to: " + namedQuery);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<T> list = null;

		try {
			
			session.beginTransaction();
			Query query = session.getNamedQuery(namedQuery);
			list = query.list();
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			
			logger.error(e.getMessage());
			session.getTransaction().rollback();
			
		} finally {
			
			session.close();
		}

		return list;
	}
	
	public T getById(Integer pk) throws SQLExceptionIFOpenDoors {		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		T entity = null;
		
		try {
		
			session.beginTransaction();
			entity = (T) session.get(getEntityClass(), pk);
	        Hibernate.initialize(entity);
	        session.getTransaction().commit();
	        
		} catch (HibernateException hibernateException) {
			
			session.getTransaction().rollback();
			
			logger.error(hibernateException.getMessage());
			//TODO: throw new SQLExceptionIFOpenDoors(hibernateException);
			
		} finally {
		
			session.close();
		}
		
		return entity;
	}

	@Override
	public List<T> find(T entity) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return null;
	}	
}