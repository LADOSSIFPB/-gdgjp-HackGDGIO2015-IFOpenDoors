package br.edu.ifpb.ifopendoors.hibernate;

import org.hibernate.Session;

import br.edu.ifpb.ifopendoors.entity.Room;

public class HibernateTest {
 
public static void main(String[] args) {
          
        Session session = HibernateUtil.getSessionFactory().openSession();
  
        session.beginTransaction();
 
        Room room = new Room();
        room.setNome("Laboratório de Informática");
        room.setTipo("Laboratório");
      
        session.save(room);
        
        session.getTransaction().commit(); 
    }
    
}