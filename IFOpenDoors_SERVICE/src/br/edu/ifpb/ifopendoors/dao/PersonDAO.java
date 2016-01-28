package br.edu.ifpb.ifopendoors.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
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

	@Override
	public Person getById(Integer idPerson) throws SQLExceptionIFOpenDoors {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Person person = null;
		
		try {
		
			session.beginTransaction();
			person = (Person) session.get(Person.class, idPerson);
	        Hibernate.initialize(person);
	        session.getTransaction().commit();
	        
		} catch (HibernateException e) {
			
			logger.error(e.getMessage());
			session.getTransaction().rollback();
			
		} finally {
			
			session.close();
		}
		
		return person;
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
}
