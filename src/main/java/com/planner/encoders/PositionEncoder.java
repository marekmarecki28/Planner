package com.planner.encoders;

import java.util.List;

import org.apache.tapestry5.ValueEncoder;

import com.planner.dao.EmployeeDAO;
import com.planner.dao.PositionsDAO;
import com.planner.entities.Employee;
import com.planner.entities.Position;

public class PositionEncoder implements ValueEncoder<Position> {
	
	private PositionsDAO positionsDAO;
	
	public PositionEncoder(PositionsDAO positionsDAO)
	{
		this.positionsDAO = positionsDAO;
	}

	@Override
	public String toClient(Position value) {
		return String.valueOf(value.getPositionId());
	}

	@Override
	public Position toValue(String id) {
		return positionsDAO.getPosition(Long.parseLong(id));
	}

}
