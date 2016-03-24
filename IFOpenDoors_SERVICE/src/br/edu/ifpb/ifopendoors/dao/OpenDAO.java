package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.ifopendoors.entity.Open;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.hibernate.HibernateUtil;

public class OpenDAO extends AbstractDAO<Integer, Open> {

	private static Logger logger = LogManager.getLogger(OpenDAO.class);

	private static OpenDAO instance;
	
	public static OpenDAO getInstance() {
		instance = new OpenDAO();
		return instance;
	}

	public void isOpenRoom(Integer idRoom) {
		
		// Consultar porta aberta.
		Session session = HibernateUtil.getSessionFactory().openSession();              
        
        try {
    		
        	String sql = "SELECT * "
            		+ " FROM tb_alocacao as al, tb_desalocacao as des "
            		+ " WHERE al.room_id_sala = :number"
            		+ " AND al.dt_abertura = "
            			+ " (SELECT max(al2.dt_abertura) "
            			+ " FROM tb_alocacao as al2 "
            			+ " WHERE al2.room_id_sala = :number)"
            		+ " AND al.id_alocacao = des.open_id_alocacao";
            
        	session.beginTransaction();  
            
        	Query query = session.createSQLQuery(sql)
            		.setParameter("number", idRoom);
            
            List result = query.list();
            
            session.getTransaction().commit();
            
		} catch (HibernateException e) {
			
			logger.error(e.getMessage());
			session.getTransaction().rollback();
			
		} finally {
			
			session.close();
		}
	}
	
	@Override
	public int delete(Integer id) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Open> getAll() throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Open getById(Integer id) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return null;
	}
}
