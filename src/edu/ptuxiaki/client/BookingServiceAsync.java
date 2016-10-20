package edu.ptuxiaki.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BookingServiceAsync {

	void checkDates(String day1, String day2, AsyncCallback<String> callback);

	void getAvailableRooms(String date1, String date2, AsyncCallback<ArrayList<RoomData>> callback);

	void addBooking(String startDate, String endDate, int userId, int roomId, AsyncCallback<Void> callback);

	void getBookings(Boolean isCheckedIn, AsyncCallback<ArrayList<BookingData>> callback);

	void checkIn(int bookingId, AsyncCallback<Void> callback);

	void checkOut(int bookingId, AsyncCallback<Void> callback);

	void getUserIdFromEmail(String email, AsyncCallback<Integer> callback);

	void getRoomIdFromName(String roomName, AsyncCallback<Integer> callback);

}
