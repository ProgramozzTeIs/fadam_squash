package pti.sb_sqashadmin_mvc.db;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;

import pti.sb_sqashadmin_mvc.model.Location;
import pti.sb_sqashadmin_mvc.model.User;

public class Database {

	private SessionFactory sessionFactory;

	public Database() {

		Configuration cfg = new Configuration();
		cfg.configure();

		this.sessionFactory = cfg.buildSessionFactory();
	}

	public void closeDb() {

		this.sessionFactory.close();

	}
	
	public List<User> getAllUser(){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<User> q = session.createSelectionQuery("SELECT u FROM User u", User.class);
		List<User> users = q.getResultList();
		
		tx.commit();
		session.close();
		
		return users;
		
	}
	
	public List<Location> getAllLocation(){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Location> q = session.createSelectionQuery("SELECT l FROM Location l", Location.class);
		List<Location> locations = q.getResultList();
		
		tx.commit();
		session.close();
		
		return locations;
		
	}

}
