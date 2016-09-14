package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.ifopendoors.entity.Person;
import br.edu.ifpb.ifopendoors.exception.SQLExceptionIFOpenDoors;
import br.edu.ifpb.ifopendoors.hibernate.HibernateUtil;

public class PersonDAO extends AbstractDAO<Integer, Person>{

	private static Logger logger = LogManager.getLogger(PersonDAO.class);

	private static PersonDAO instance;
	
	public static PersonDAO getInstance() {
		instance = new PersonDAO();
		return instance;
	}

	public Person login(String email, String password){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Person pessoa = null;
		
		try {			
			logger.info("Verificando o login: " + email);
			
			String hql = "from Person as p"
					+ " where p.email = :email"
					+ " and p.password = :password";
			
			Query query = session.createQuery(hql);			
			query.setParameter("email", email);
			query.setParameter("password", password);
			
			pessoa = (Person) query.uniqueResult();
	        
		} finally {
		
			session.close();
		}
		
		return pessoa;
	}
	
	@Override
	public List<Person> getAll() throws SQLExceptionIFOpenDoors {
		return super.getAll("Person.getAll");
	}
	
	@Override
	public int delete(Integer pk) throws SQLExceptionIFOpenDoors {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Person.class;
	}	
}
