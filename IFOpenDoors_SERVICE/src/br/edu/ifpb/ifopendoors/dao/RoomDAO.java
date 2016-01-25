package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.ifopendoors.entity.Room;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.hibernate.HibernateUtil;

public class RoomDAO implements GenericDAO<Integer, Room>{

	private static Logger logger = LogManager.getLogger(RoomDAO.class);

	private static RoomDAO instance;
	
	public static RoomDAO getInstance() {
		instance = new RoomDAO();
		return instance;
	}

	@Override
	public int insert(Room room) throws SQLExceptionIFOpenDoors {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		Integer id;
		
		try {
			
			session.beginTransaction();
			id = (Integer) session.save(room);
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
	public void update(Room entity) throws SQLExceptionIFOpenDoors {
		
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
	public int delete(Integer pk) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Room> getAll() throws SQLExceptionIFOpenDoors {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Room> roons = null;

		try {
			
			session.beginTransaction();
			Query query = session.getNamedQuery("Room.getAll");
			roons = query.list();
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			
			logger.error(e.getMessage());
			session.getTransaction().rollback();
			
		} finally {
			
			session.close();
		}

		return roons;
	}

	@Override
	public Room getById(Integer idRoom) throws SQLExceptionIFOpenDoors {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Room room = null;
		
		try {
		
			session.beginTransaction();
			room = (Room) session.get(Room.class, idRoom);
	        Hibernate.initialize(room);
	        session.getTransaction().commit();
	        
		} catch (HibernateException e) {
			
			logger.error(e.getMessage());
			session.getTransaction().rollback();
			
		} finally {
			
			session.close();
		}
		
		return room;
	}

	@Override
	public List<Room> find(Room entity) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return null;
	}
}
