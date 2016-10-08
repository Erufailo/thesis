package edu.ptuxiaki.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("room")
public interface RoomService extends RemoteService{

	public void addRoomFromAdmin(String roomName, int capacity, String description, int available, String price);
	
	public void editRoomFromAdmin(String roomName, int capacity, String description, int available, String price);
	
	public void deleteRoom(String roomName);
	
	public ArrayList<RoomData> getAllRooms();
	
	public boolean roomAvailable(String roomName);
	
	public RoomData getRoom(String roomName);
	
}
