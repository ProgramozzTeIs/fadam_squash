package pti.sb_sqashadmin_mvc.db;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;

import pti.sb_sqashadmin_mvc.model.Location;
import pti.sb_sqashadmin_mvc.model.Match;
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

	public User getUserByNameAndPassword(String userName, String password) {
		
		Session session =sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<User> query = session.createSelectionQuery("SELECT u FROM User u WHERE u.name = ?1 AND u.password = ?2",User.class);
		query.setParameter(1, userName);
		query.setParameter(2, password);
		
		List<User> users = query.getResultList();
		
		User user = null;
		
		if(users.size() > 0) {
			
			user = users.get(0);
			
		}
		
		tx.commit();
		session.close();
		
		return user;
	}

	public void updateUser(User user) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.merge(user);
		
		tx.commit();
		session.close();
		
	}

	public User getUserById(int id) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = session.get(User.class, id);
		
		tx.commit();
		session.close();
		
		return user;
	}

	public List<Match> getAllMatchs(Integer playerId, Integer locationId) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		String hqlString = "";
		
		SelectionQuery<Match> q = null;
		
		if (playerId != null) {
			
			hqlString = "SELECT m FROM Match m WHERE m.user1Id = ?1 OR m.user2Id = ?1";
			q = session.createSelectionQuery(hqlString, Match.class);
			q.setParameter(1, playerId);
		}
		
		else if (locationId != null) {
			
			hqlString = "SELECT m FROM Match m WHERE m.placeId = ?1";
			q = session.createSelectionQuery(hqlString, Match.class);
			q.setParameter(1, locationId);
			
		}
		
		else {
			
			hqlString = "SELECT m FROM Match m";
			q = session.createSelectionQuery(hqlString, Match.class);
			
		}
		
		
		
		List<Match> matchs = q.getResultList();
		
		tx.commit();
		session.close();
		
		
		return matchs;
	}

	public void inserNewUser(User user) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(user);
		
		tx.commit();
		session.close();
	}

	public void inserNewLocation(Location location) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(location);
		
		tx.commit();
		session.close();
		
	}

	public void insertNewMatch(Match match) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(match);
		
		tx.commit();
		session.close();
		
		
	}

}
