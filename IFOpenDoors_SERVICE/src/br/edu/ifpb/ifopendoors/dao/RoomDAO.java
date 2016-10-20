package br.edu.ifpb.ifopendoors.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

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
	
	public Open getLastOpen(int idRoom){
		
		Session session = HibernateUtil.getSessionFactory().openSession();		
		
		String hql = "from Open as o"
				+ " where o.id not in ("
				+ " 	select c.open.id"
				+ "		from Close as c"
				+ "		where c.open.room.id = :idSala"
				+ ")"
				+ " and o.room.id = :idSala";
		
		Query query = session.createQuery(hql);
		query.setParameter("idSala", idRoom);
	
		Open open =  (Open) query.uniqueResult();	
		
		return open;
	}

	@Override
	public List<Room> getAll() throws SQLExceptionIFOpenDoors {
		
		return super.getAll("Room.getAll");
		
	}
	
	
	public List<Room> getAllByName(String nome) throws SQLExceptionIFOpenDoors{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		List<Room> roons = null;
		
		try {
			
			String hql = "from tb_sala as sala where sala.nm_sala = :nome";
			
			Query query = session.createQuery(hql);
			query.setParameter("nome", nome);
			
			roons = (List<Room>) query.list();
	        
		}finally {
		
			session.close();
		}
		
		return roons;
	}
	
	
	@Override
	public int delete(Integer pk) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Room.class;
	}	
}
