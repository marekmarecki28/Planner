package com.planner.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table( name = "HOTEL" )
public class Hotel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1313392499439081429L;
	
	public Hotel(){
	}
	
	public Hotel(Long hotel_id, String hotel_name, String description) {
		this.hotelId = hotel_id;
		this.hotelName = hotel_name;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	@Column(name = "HOTEL_ID")
	private Long hotelId;
	
	@Column(name = "HOTEL_NAME")
	private String hotelName;
	
	private String description;

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotel_id) {
		this.hotelId = hotel_id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotel_name) {
		this.hotelName = hotel_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
