package com.planner.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.planner.dao.PositionsDAO;
import com.planner.entities.Employee;
import com.planner.entities.Position;

public class PositionsDAOImpl implements PositionsDAO {
	
	private final Session session;

	public PositionsDAOImpl(Session s) {
		session = s;
	}
	
	@Override
	public List<Position> getPositions(Long hotelId) {
		List<Position> positions = session.createQuery("from Position where hotel_id = " + hotelId).list();
		return positions;
	}

	@Override
	public Position getPosition(Long positionId) {
		Position position = (Position) session.createQuery("from Position where position_id = " + positionId).uniqueResult();
		return position;
	}

	@Override
	public void createPosition(Position position) {
		Transaction tx = null;
		try{
	         tx = session.getTransaction();
			 session.save(position); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         System.out.println("ERRRORORRO!!!");
	         e.printStackTrace(); 
	      }
	}

}
