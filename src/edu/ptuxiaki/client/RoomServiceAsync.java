package edu.ptuxiaki.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RoomServiceAsync {

	void addRoomFromAdmin(String roomName, int capacity, String description, int available, String price,
			AsyncCallback<Void> callback);

	void editRoomFromAdmin(String roomName, int capacity, String description, int available, String price,
			AsyncCallback<Void> callback);

	void deleteRoom(String roomName, AsyncCallback<Void> callback);

	void getAllRooms(AsyncCallback<ArrayList<RoomData>> callback);

	void roomAvailable(String roomName, AsyncCallback<Boolean> callback);

	void getRoom(String roomName, AsyncCallback<RoomData> callback);

}
