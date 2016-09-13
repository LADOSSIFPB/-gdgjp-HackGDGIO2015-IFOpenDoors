package br.edu.ifpb.ifopendoors.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.ifopendoors.entity.Door;
import br.edu.ifpb.ifopendoors.entity.Open;
import br.edu.ifpb.ifopendoors.entity.Person;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.hibernate.HibernateUtil;

public class DoorDAO extends AbstractDAO<Integer, Door>{

	private static Logger logger = LogManager.getLogger(DoorDAO.class);

	private static DoorDAO instance;
	
	public static DoorDAO getInstance() {
		instance = new DoorDAO();
		return instance;
	}

	@Override
	public Door getById(Integer idDoor) throws SQLExceptionIFOpenDoors {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Door door = null;
		
		try {
		
			session.beginTransaction();
			door = (Door) session.get(Door.class, idDoor);
	        Hibernate.initialize(door);
	        session.getTransaction().commit();
	        
		} catch (HibernateException e) {
			
			logger.error(e.getMessage());
			session.getTransaction().rollback();
			
		} finally {
			
			session.close();
		}
		
		return door;
	}
	
	public Door checkKey(Door doorCheck) throws SQLExceptionIFOpenDoors {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Door door = null;
		
		try {						
			String hql = "from Door as d"
					+ " where d.ip = :email"
					+ " and d.key= :key";
			
			Query query = session.createQuery(hql);			
			query.setParameter("ip", doorCheck.getIp());
			query.setParameter("key", doorCheck.getKey());
			
			door = (Door) query.uniqueResult();
	        
		} finally {
		
			session.close();
		}
		
		return door;
	}
	
	
	public List<Door>  getAllWithoutRoom(){
		
		Session session = HibernateUtil.getSessionFactory().openSession();		
		
		String hql = "from Door as d"
				+ " where d.id not in ("
				+ " 	select r.door.id"
				+ "		from Room as r"
				+ ")";
		
		Query query = session.createQuery(hql);
		
		List<Door> door = new ArrayList<Door>();
		door = query.list();	
		
		return door;
	}

	@Override
	public List<Door> getAll() throws SQLExceptionIFOpenDoors {
		return super.getAll("Door.getAll");
	}
	
	@Override
	public int delete(Integer pk) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return 0;
	}
}
