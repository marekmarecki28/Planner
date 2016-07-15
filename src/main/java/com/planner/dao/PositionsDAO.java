package com.planner.dao;

import java.util.List;

import com.planner.entities.Position;

public interface PositionsDAO {

	List<Position> getPositions(Long hotelId);
	Position getPosition(Long positionId);
	public void createPosition(Position position);

}
