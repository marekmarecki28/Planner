package com.planner.encoders;

import org.apache.tapestry5.ValueEncoder;

import com.planner.dao.EmployeeDAO;
import com.planner.dao.PositionsDAO;
import com.planner.entities.Employee;
import com.planner.entities.Positions;

public class PositionEncoder implements ValueEncoder<Positions> {
	
	private PositionsDAO positionsDAO;
	
	public PositionEncoder(PositionsDAO positionsDAO)
	{
		this.positionsDAO = positionsDAO;
	}

	@Override
	public String toClient(Positions value) {
		return String.valueOf(value.getPositionId());
	}

	@Override
	public Positions toValue(String id) {
		return positionsDAO.getPositions(Long.parseLong(id));
	}

}
