package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.edu.ifpb.ifopendoors.entity.TypeRoom;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.hibernate.HibernateUtil;

public class TypeRoomDAO extends AbstractDAO<Integer, TypeRoom>{

	private static Logger logger = LogManager.getLogger(TypeRoomDAO.class);

	private static TypeRoomDAO instance;
	
	public static TypeRoomDAO getInstance() {
		instance = new TypeRoomDAO();
		return instance;
	}

	@Override
	public TypeRoom getById(Integer idTypeRoom) throws SQLExceptionIFOpenDoors {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		TypeRoom typeRoom = null;
		
		try {
		
			session.beginTransaction();
			typeRoom = (TypeRoom) session.get(TypeRoom.class, idTypeRoom);
	        Hibernate.initialize(typeRoom);
	        session.getTransaction().commit();
	        
		} catch (HibernateException e) {
			
			logger.error(e.getMessage());
			session.getTransaction().rollback();
			
		} finally {
			
			session.close();
		}
		
		return typeRoom;
	}

	@Override
	public List<TypeRoom> getAll() throws SQLExceptionIFOpenDoors {
		return super.getAll("TypeRoom.getAll");
	}
	
	@Override
	public int delete(Integer pk) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return 0;
	}	
}
