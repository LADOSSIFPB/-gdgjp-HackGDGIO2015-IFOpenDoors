package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.ifopendoors.entity.Close;
import br.edu.ifpb.ifopendoors.entity.Door;
import br.edu.ifpb.ifopendoors.entity.Open;
import br.edu.ifpb.ifopendoors.entity.Room;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.hibernate.HibernateUtil;

public class RoomDAO extends AbstractDAO<Integer, Room>{

	private static Logger logger = LogManager.getLogger(RoomDAO.class);

	private static RoomDAO instance;
	
	public static RoomDAO getInstance() {
		instance = new RoomDAO();
		return instance;
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
	
	public boolean isOpen(Room room){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql = "from Open as o"
				+ " where o.room = :room"
				+ " order by o.time desc";
		
		Query query = session.createQuery(hql);
		query.setParameter("room", room);
		
		List opens = query.list();
		Open open = (Open) opens.get(0);
		
		hql = "from Close as c"
				+ " where c.open = :open"
				+ " and c.time > :openTime";
		
		Query query2 = session.createQuery(hql);
		query2.setParameter("open", open);
		query2.setParameter("openTime", open.getTime());
	
		Close close = (Close) query2.uniqueResult();
		
		if(close!=null)
			return false;		
		
		return true;
		
	}

	@Override
	public List<Room> getAll() throws SQLExceptionIFOpenDoors {
		return super.getAll("Room.getAll");
	}
	
	@Override
	public int delete(Integer pk) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return 0;
	}	
}
