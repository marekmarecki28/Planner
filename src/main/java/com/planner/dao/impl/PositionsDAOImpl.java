package com.planner.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.planner.dao.PositionsDAO;
import com.planner.entities.Employee;
import com.planner.entities.Position;

public class PositionsDAOImpl implements PositionsDAO {
	
	private final Session session;

	public PositionsDAOImpl(Session s) {
		session = s;
	}
	
	@Override
	public List<Position> getPositions() {
		List<Position> positions = session.createCriteria(Position.class).list();
		return positions;
	}

	@Override
	public Position getPosition(Long positionId) {
		Position position = (Position) session.createQuery("from Position where position_id = " + positionId).uniqueResult();
		return position;
	}

}
