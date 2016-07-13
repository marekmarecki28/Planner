package com.planner.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table( name = "POSITIONS" )
public class Position {
	
	public Position(){}
	
	public Position(Long positionId, String description, Long hotelId) {
		this.positionId = positionId;
		this.description = description;
		this.hotelId = hotelId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	@Column(name = "POSITION_ID")
	private Long positionId;
	@Column(name = "HOTEL_ID")
	private Long hotelId;
	private String description;
	
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
