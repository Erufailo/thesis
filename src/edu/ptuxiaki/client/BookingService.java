package edu.ptuxiaki.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("booking")
public interface BookingService extends RemoteService {
	public String checkDates(String day1, String day2);
	
	public ArrayList<RoomData> getAvailableRooms(String date1, String date2);
	
	public void addBooking(String startDate, String endDate, int userId, int roomId);
	
	public ArrayList<BookingData> getBookings(Boolean isCheckedIn);
	
	public void checkIn(int bookingId);
	
	public void checkOut(int bookingId);
	
	public int getUserIdFromEmail(String email);
	
	public int getRoomIdFromName(String roomName);
	
	public boolean isOldDates(String day1, String day2);
	
	public ArrayList<BookingData> getBooking(int userId);
	
}
