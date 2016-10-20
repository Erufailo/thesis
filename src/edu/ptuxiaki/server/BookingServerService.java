package edu.ptuxiaki.server;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.ptuxiaki.client.BookingData;
import edu.ptuxiaki.client.BookingService;
import edu.ptuxiaki.client.RoomData;

public class BookingServerService extends RemoteServiceServlet implements BookingService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Database db = new Database();

	@Override
	public String checkDates(String day1, String day2) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	try {
			Date date1 = sdf.parse(day1);
			Date date2 = sdf.parse(day2);
			
			//calculate the days
			int daysBetween = CalendarUtil.getDaysBetween(date1,date2);
			System.out.println(sdf.format(date1));
			System.out.println(sdf.format(date2));
			System.out.println(daysBetween);
			return daysBetween+"days";
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
		return null;
	}

	@Override
	public ArrayList<RoomData> getAvailableRooms(String date1, String date2) {
		ArrayList<RoomData> rooms= null;
		try {
			
			rooms= db.getAvailableRooms(date1, date2);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rooms;
	}

	@Override
	public void addBooking(String startDate, String endDate, int userId, int roomId) {
		try {
			db.addBooking(startDate, endDate, userId, roomId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public ArrayList<BookingData> getBookings(Boolean isCheckedIn) {
		ArrayList<BookingData> bookings = null;
		try {
			bookings= db.getBookings(isCheckedIn);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookings;
	}

	@Override
	public void checkIn(int bookingId) {
		try {
			db.checkIn(bookingId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void checkOut(int bookingId) {
		try {
			db.checkOut(bookingId);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getUserIdFromEmail(String email) {
		int userId=0;
		try {
			db.getUserIdFromEmail(email);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}

	@Override
	public int getRoomIdFromName(String roomName) {
		int roomId=0;
		try {
			db.getRoomIdFromName(roomName);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roomId;
	}
	
	

}
