package edu.ptuxiaki.client;

import java.io.Serializable;

public class RoomData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String roomName;
	private int capacity;
	private String description;
	private int available;
	private String price;
	
	
	
	public RoomData() {
		// TODO Auto-generated constructor stub
	}
	
	
	public RoomData(String roomName, int capacity, String description, int available, String price) {
		super();
		this.roomName = roomName;
		this.capacity = capacity;
		this.description = description;
		this.available = available;
		this.price = price;
	}
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
	
}
