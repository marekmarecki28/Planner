package com.planner.dao.impl;

import org.hibernate.Session;

import com.planner.dao.PositionsDAO;
import com.planner.entities.Employee;
import com.planner.entities.Positions;

public class PositionsDAOImpl implements PositionsDAO {
	
	private final Session session;

	public PositionsDAOImpl(Session s) {
		session = s;
	}
	
	@Override
	public Positions getPositions(long parseLong) {
		Positions positions = (Positions) session.createQuery("from Positions where employee_id = " + parseLong).uniqueResult();
		return positions;
	}

}
