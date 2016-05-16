package com.planner.dao;

import java.util.List;

import com.planner.entities.Position;

public interface PositionsDAO {

	List<Position> getPositions();
	Position getPosition(Long positionId);

}
