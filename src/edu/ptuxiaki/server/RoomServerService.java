package edu.ptuxiaki.server;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.ptuxiaki.client.RoomData;
import edu.ptuxiaki.client.RoomService;

public class RoomServerService extends RemoteServiceServlet implements RoomService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Database db = new Database();
	
	
	@Override
	public void addRoomFromAdmin(String roomName, int capacity, String description, int available, String price) {
		try {
			db.addRoomFromAdmin(roomName, capacity, description, available, price);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void editRoomFromAdmin(String roomName, int capacity, String description, int available, String price) {
		try {
			db.editRoomFromAdmin(roomName, capacity, description, available, price);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void deleteRoom(String roomName) {
		try {
			db.deleteRoom(roomName);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public ArrayList<RoomData> getAllRooms() {
		ArrayList<RoomData> rooms = null;
		try {
			rooms= db.getAllRooms();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return rooms;
	}


	@Override
	public boolean roomAvailable(String roomName) {
		boolean available = false;
		try {
			available= db.roomAvailable(roomName);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return available;
	}


	@Override
	public RoomData getRoom(String roomName) {
		RoomData room = null;
		try {
			room = db.getRoom(roomName);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return room;
	}
	
	
}
