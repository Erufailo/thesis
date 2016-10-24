package edu.ptuxiaki.client;

import java.io.Serializable;

public class BookingData implements Serializable{
	
	
	private int bookingId;
	private String roomName;
	private String startDate;
	private String endDate;
	private String firstName;
	private String lastName;
	private int bookingStatus;
	private String price;
	
	
	public BookingData() {
		this.bookingId = -1;
		this.roomName = null;
		this.startDate = null;
		this.endDate = null;
		this.firstName = null;
		this.lastName = null;
		this.bookingStatus = 0;
		this.price = null;
		
	}

	

	public BookingData(int bookingId, String roomName, String startDate, String endDate, String firstName,
			String lastName, int bookingStatus, String price) {
		super();
		this.bookingId = bookingId;
		this.roomName = roomName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bookingStatus = bookingStatus;
		this.price = price;
	}




	public int getBookingStatus() {
		return bookingStatus;
	}




	public void setBookingStatus(int bookingStatus) {
		this.bookingStatus = bookingStatus;
	}




	public String getPrice() {
		return price;
	}




	public void setPrice(String price) {
		this.price = price;
	}







	public int getBookingId() {
		return bookingId;
	}


	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}


	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
