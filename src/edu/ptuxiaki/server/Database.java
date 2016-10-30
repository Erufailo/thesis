package edu.ptuxiaki.server;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import edu.ptuxiaki.client.BookingData;
import edu.ptuxiaki.client.RoomData;
import edu.ptuxiaki.client.UserData;


public class Database {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String DB_NAME = "HotelDB2997";
	static final String USER = "root";
	static final String PASS = "123";
	private int id = 0;

	/**
	 * Initialize connection with Database
	 *
	 * @return returns an SQL connection
	 */
	public Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName(JDBC_DRIVER);
		conn = (Connection) DriverManager.getConnection(DB_URL + DB_NAME, USER, PASS);
		return conn;
	}

	/**
	 * Adds a new user to the database
	 * 
	 * @param name
	 * @param surname
	 * @param email
	 * @param password
	 * @param tel
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	
	//#####               USERS      ###########
	public void register(String name, String surname, String email, String password, String tel)
			throws ClassNotFoundException, SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getMySQLConnection();
			String query = "insert into users(email, firstname, lastname, telephone, role ,password ) values(?,?,?,?,?,?)";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, name);
			pstmt.setString(3, surname);
			pstmt.setString(4, tel);
			pstmt.setString(5, "customer");
			pstmt.setString(6, password);
			pstmt.executeUpdate();
			
			System.out.println("added ");

		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		

	}
	
	/**
	 * Finds the user passhash in the database given his email 
	 * 
	 * @param email
	 * @param Password hash
	 * @return the user name and surname as a String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String login(String email) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			conn = getMySQLConnection();
			String query = "SELECT * FROM users WHERE email =? ";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, email);
			
			result = pstmt.executeQuery();
			if (result.next())
				//return passhash for check
	
				return (result.getString(7));

		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	
	/**
	 * 
	 * 
	 * @return returns a user listed in the database as an UserData Object
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserData getUser(String email) throws ClassNotFoundException, SQLException {

        UserData user = null;
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;       
		PreparedStatement pstmt = null;
		

        //initialize the variables to contain the results
       
//        String fname = "";
//        String surname  = "";
//        String email= "";
//        String tel = "";
//        String role = "";

        try {
            //initialize the SQL connection 
        		  		
    			conn = getMySQLConnection();
    			String query = "SELECT * FROM users WHERE email =? ";
    			pstmt = (PreparedStatement) conn.prepareStatement(query);
    			pstmt.setString(1, email);
    			
    			result = pstmt.executeQuery();
    			if (result.next()){
    				user = new UserData();
    				user.setId(result.getInt(1));
    				user.setEmail(result.getString(2));
    				user.setName(result.getString(3));
    				user.setSurname(result.getString(4));
    				user.setTel(result.getString(5));
    				user.setRole(result.getString(6));
    				
    			}            
    			System.out.println("Get User: " +user.getId());
            //catch the exception if any
        } catch (SQLException e) {
            e.printStackTrace();
            //close all the open connections
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }
	/**
	 * register from admin panel.
	 * @param name
	 * @param surname
	 * @param email
	 * @param password
	 * @param tel
	 * @param role
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void addUserFromAdmin(String name, String surname, String email, String password, String tel,String role)
			throws ClassNotFoundException, SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getMySQLConnection();
			String query = "insert into users(email, firstname, lastname, telephone, role ,password ) values(?,?,?,?,?,?)";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, name);
			pstmt.setString(3, surname);
			pstmt.setString(4, tel);
			pstmt.setString(5, role);
			pstmt.setString(6, password);
			pstmt.executeUpdate();
			
			System.out.println("added ");

		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	
		

	}
	public void editUserFromAdmin(String name, String surname, String email , String tel,String role)
			throws ClassNotFoundException, SQLException {
		ResultSet result = null;  
		Connection conn = null;
		PreparedStatement pstmt = null;
		int id = -1;

		try {
			conn = getMySQLConnection();
			String query = "select user_id FROM users WHERE email = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, email);
			result= pstmt.executeQuery();
			if (result.next()){
				id= result.getInt(1);
				System.out.println(id);
			}
			pstmt.close();
			pstmt= null;
			String query2 = "UPDATE users SET email=?, firstname=?, lastname=?, telephone=?, role=? WHERE user_id="+id+"";
			pstmt = (PreparedStatement) conn.prepareStatement(query2);
			pstmt.setString(1, email);
			pstmt.setString(2, name);
			pstmt.setString(3, surname);
			pstmt.setString(4, tel);
			pstmt.setString(5, role);
			pstmt.executeUpdate();
			
			System.out.println("updated ");

		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	
		

	}
	
	
	/**
	 * 
	 * @return ArrayList of UserData
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<UserData> getAllUsers() throws ClassNotFoundException, SQLException {
		
		ArrayList<UserData> users = new  ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		

		// initialize the variables to contain the results
		String email = "";
		String name = "";
		String surname = "";
		String tel = "";
		String role= "";

		try {
			// initialize the SQL connection
			conn = getMySQLConnection();
			stmt = (Statement) conn.createStatement();
			// set the query and execute it
			String query = "SELECT * FROM users";
			result = stmt.executeQuery(query);

			// get the results and put them in the variables
			while (result.next()) {
				email = result.getString(2);
				name = result.getString(3);
				surname = result.getString(4);
				tel = result.getString(5);
				role = result.getString(6);

				String temp = name + " " + surname + " " + email + " " + tel+" " + role;
				System.out.println(temp);
				
				UserData data = new UserData(name,surname,email,tel,role);
				users.add(data);

			}
			// catch the exception if any
		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return users;

	}
	/**
	 * Deletes a Person from the Database given his email
	 * 
	 * @param email
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void deleteUser(String email) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getMySQLConnection();
			String query = "DELETE FROM users WHERE email =?";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, email);
		

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}
	
	
	
	
	//#######                  END USERS                                ###########
	
	//****************************************************************************************************************
		
	//######                   ROOMS                          ###########
	
	public void addRoomFromAdmin(String roomName, int capacity, String description, int available, String price)
			throws ClassNotFoundException, SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getMySQLConnection();
			String query = "insert into rooms(room_name, capacity, description, available, price) values(?,?,?,?,?)";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, roomName);
			pstmt.setInt(2, capacity);
			pstmt.setString(3, description);
			pstmt.setInt(4, available);
			pstmt.setString(5, price);
			pstmt.executeUpdate();
			
			System.out.println("added ");

		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	
		

	}
	
	public void editRoomFromAdmin(String roomName, int capacity, String description, int available, String price)
			throws ClassNotFoundException, SQLException {
		ResultSet result = null;  
		Connection conn = null;
		PreparedStatement pstmt = null;
		int id = -1;

		try {
			conn = getMySQLConnection();
			String query = "select room_id FROM rooms WHERE room_name = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, roomName);
			result= pstmt.executeQuery();
			if (result.next()){
				id= result.getInt(1);
				System.out.println(id);
			}
			pstmt.close();
			pstmt= null;
			String query2 = "UPDATE rooms SET room_name=?, capacity=?, description=?, available=?, price=? WHERE room_id="+id+"";
			pstmt = (PreparedStatement) conn.prepareStatement(query2);
			pstmt.setString(1, roomName);
			pstmt.setInt(2, capacity);
			pstmt.setString(3, description);
			pstmt.setInt(4, available);
			pstmt.setString(5, price);
			pstmt.executeUpdate();
			
			System.out.println("updated ");

		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	
		

	}
	
	
	public void deleteRoom(String roomName) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getMySQLConnection();
			String query = "DELETE FROM rooms WHERE room_name =?";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, roomName);
		

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}
	
	
public ArrayList<RoomData> getAllRooms() throws ClassNotFoundException, SQLException {
		
		ArrayList<RoomData> rooms = new  ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		

		// initialize the variables to contain the results
		String roomName = "";
		int capacity = 0;
		String description = "";
		int available = 0;
		String price= "";

		try {
			// initialize the SQL connection
			conn = getMySQLConnection();
			stmt = (Statement) conn.createStatement();
			// set the query and execute it
			String query = "SELECT * FROM rooms";
			result = stmt.executeQuery(query);

			// get the results and put them in the variables
			while (result.next()) {
				roomName = result.getString(2);
				capacity = result.getInt(3);
				description = result.getString(4);
				available = result.getInt(5);
				price = result.getString(6);

				String temp = roomName + " " + capacity + " " + description + " " + available+" " + price;
				System.out.println(temp);
				
				RoomData data = new RoomData(roomName, capacity, description, available, price);
				rooms.add(data);

			}
			// catch the exception if any
		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return rooms;

	}
public RoomData getRoom(String roomName) throws ClassNotFoundException, SQLException {

    RoomData room = null;
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet result = null;       
	PreparedStatement pstmt = null;

    try {
        //initialize the SQL connection 
    		  		
			conn = getMySQLConnection();
			String query = "SELECT * FROM rooms WHERE room_name =? ";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, roomName);
			
			result = pstmt.executeQuery();
			if (result.next()){
				room = new RoomData();
				room.setId(result.getInt(1));
				room.setRoomName(result.getString(2));
				room.setCapacity(result.getInt(3));
				room.setDescription(result.getString(4));
				room.setAvailable(result.getInt(5));
				room.setPrice(result.getString(6));
				
			}            
			System.out.println("get Room: " +room.getId());
			
        //catch the exception if any
    } catch (SQLException e) {
        e.printStackTrace();
        //close all the open connections
    } finally {
        if (result != null) {
            result.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    return room;
}
	
	
	
	
	
	public boolean roomAvailable(String roomName) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		int available = 0;
		boolean availability = false;

		try {
			conn = getMySQLConnection();
			String query = "SELECT FROM rooms WHERE room_name =?";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, roomName);
		
			result = pstmt.executeQuery(query);
			
			if(result.next()){
				available = result.getInt(5);
				if(available == 0){
					availability = false;
				}else{
					availability = true;
				}
				
			}
			//pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return availability;
	}
		
		
		

	//#######                  END ROOMS                                ###########
	
	//****************************************************************************************************************
		
	//######                   BOOKINGS                          ###########
	
	
	
	public ArrayList<RoomData> getAvailableRooms(String date1, String date2) throws ClassNotFoundException, SQLException{
		
		
		
		ArrayList<RoomData> rooms = new  ArrayList<>();
		ArrayList<RoomData> tempRooms = new  ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		

		// initialize the variables to contain the results
		int id= 0;
		String roomName = "";
		int capacity = 0;
		String description = "";
		int available = 0;
		String price= "";

		try {
			// initialize the SQL connection
			conn = getMySQLConnection();
			stmt = (Statement) conn.createStatement();
			// set the query and execute it
			String query = "select room_id from bookings  WHERE STR_TO_DATE('"+date1+"', '%d-%m-%Y') >= date(start_date) "
														+"AND STR_TO_DATE('"+date2+"', '%d-%m-%Y')<=date(end_date)";
			result = stmt.executeQuery(query);

			// get the results and put them in the variables
			while (result.next()) {
				id=result.getInt(1);
				

				String temp = id+" "+roomName + " " + capacity + " " + description + " " + available+" " + price;
				System.out.println(temp);
				
				RoomData tempData = new RoomData(id, roomName, capacity, description, available, price);
				tempRooms.add(tempData);

			}
			
			result.close();
			stmt.close();
			result=null;
			stmt=null;
			stmt = (Statement) conn.createStatement();
			
			String query2 = "SELECT * FROM rooms";
			result = stmt.executeQuery(query2);
			
			while (result.next()) {
				id=result.getInt(1);
				roomName = result.getString(2);
				capacity = result.getInt(3);
				description = result.getString(4);
				available = result.getInt(5);
				price = result.getString(6);
				
				
				String temp = roomName + " " + capacity + " " + description + " " + available+" " + price;
				System.out.println(temp);
				//if(available ==1){
					RoomData data = new RoomData(id, roomName, capacity, description, available, price);
					rooms.add(data);
				//}
			}		
			for(int i=0; i<tempRooms.size();i++){
				for(int j=0; j<rooms.size();j++){
					if(tempRooms.get(i).getId()==rooms.get(j).getId()){	
						rooms.remove(j);
						System.out.println("MPIKAAA");
					}
				
					}

				}
			
						// catch the exception if any
		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return rooms;

	}
	
	public void addBooking(String startDate, String endDate, int userId, int roomId)
			throws ClassNotFoundException, SQLException, ParseException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String totalPrice="no room";

		try {
			
			conn = getMySQLConnection();
			String roomQuery = "SELECT price FROM rooms WHERE room_id = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(roomQuery);
			pstmt.setInt(1, roomId);
			result= pstmt.executeQuery();
			while (result.next()){
				String price = result.getString(1);
				System.out.println("price: "+price);
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		    	
				Date date1 = sdf.parse(startDate);
				Date date2 = sdf.parse(endDate);
					
					//calculate the days
				int daysBetween = CalendarUtil.getDaysBetween(date1,date2);
				totalPrice = (Integer.parseInt(price)*daysBetween)+"";
				
			}
			
			System.out.println("ADD BOOKING: "+startDate +" "+endDate+" "+userId+" "+roomId+" "+totalPrice);
			
			pstmt.close();
			pstmt= null;
			String query = "insert into bookings(start_date, end_date, user_id, room_id, booking_status, total_price) values(STR_TO_DATE(?, '%d-%m-%Y'),STR_TO_DATE(?, '%d-%m-%Y'),?,?,?,?)";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setInt(3, userId);//bug
			pstmt.setInt(4, roomId);
			pstmt.setInt(5, 0);
			pstmt.setString(6, totalPrice);
			pstmt.executeUpdate();
			
			System.out.println("added ");

		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	
		

	}
	

	public ArrayList<BookingData> getBookings(Boolean isCheckedIn) throws ClassNotFoundException, SQLException{
		
		
		
		ArrayList<BookingData> bookings = new  ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		String query="";
		
		
		

		// initialize the variables to contain the results
		
	    int bookingId=0;
		String roomName=null;
		String startDate=null;
		String endDate=null;
		String firstName=null;
		String lastName=null;
		int bookingStatus=0;
		String price=null;

		try {
			// initialize the SQL connection
			conn = getMySQLConnection();
			stmt = (Statement) conn.createStatement();
			// set the query and execute it
			if(isCheckedIn){
				query = "select * FROM bookings inner join rooms on bookings.room_id = rooms.room_id"
												+" inner join users on bookings.user_id = users.user_id"
												+" WHERE booking_status = 1";
			}else{
				query = "select * FROM bookings inner join rooms on bookings.room_id = rooms.room_id inner join users on bookings.user_id = users.user_id";
						
			}
			
			
			result = stmt.executeQuery(query);
		
			// get the results and put them in the variables
			while (result.next()) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
				bookingId=result.getInt(1);
				startDate =formatter.format(result.getDate(2));
				endDate =formatter.format(result.getDate(3));
				bookingStatus= result.getInt(6);
				price= result.getString(7);
				roomName = result.getString(9);
				firstName = result.getString(16);
				lastName = result.getString(17);
				
				
				BookingData data = new BookingData(bookingId, roomName, startDate, endDate, firstName, lastName, bookingStatus, price);
				bookings.add(data);
			}
			
			// catch the exception if any
		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return bookings;

	}
	
public ArrayList<BookingData> getBooking(int userId) throws ClassNotFoundException, SQLException{
		
		
		
		ArrayList<BookingData> bookings = new  ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		String query="";
		
		
		

		// initialize the variables to contain the results
		
	    int bookingId=0;
		String roomName=null;
		String startDate=null;
		String endDate=null;
		String firstName=null;
		String lastName=null;
		int bookingStatus=0;
		String price=null;

		try {
			// initialize the SQL connection
			conn = getMySQLConnection();
			stmt = (Statement) conn.createStatement();
			// set the query and execute it
			
				query = "select * FROM bookings inner join rooms on bookings.room_id = rooms.room_id"
												+" inner join users on bookings.user_id = users.user_id"
												+" WHERE users.user_id="+userId+"";
			
			
			
			result = stmt.executeQuery(query);
		
			// get the results and put them in the variables
			while (result.next()) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
				bookingId=result.getInt(1);
				startDate =formatter.format(result.getDate(2));
				endDate =formatter.format(result.getDate(3));
				bookingStatus= result.getInt(6);
				price= result.getString(7);
				roomName = result.getString(9);
				firstName = result.getString(16);
				lastName = result.getString(17);
				
				
				BookingData data = new BookingData(bookingId, roomName, startDate, endDate, firstName, lastName, bookingStatus, price);
				bookings.add(data);
			}
			
			// catch the exception if any
		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return bookings;

	}
	
	
	
	
	public void checkIn(int bookingId)throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		

		try {
			conn = getMySQLConnection();
			
			String query = "UPDATE bookings SET booking_status = 1 WHERE bookings_id=?";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setInt(1, bookingId);
		
			pstmt.executeUpdate();
			
			System.out.println("updated checkIn");

		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		
		
		
	}
public void checkOut(int bookingId)throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		

		try {
			conn = getMySQLConnection();
			
			String query = "DELETE FROM bookings WHERE bookings_id=?";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setInt(1, bookingId);
		
			pstmt.executeUpdate();
			
			System.out.println("updated checkOut");

		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		
		
		
		
	}
	

public int getUserIdFromEmail(String email) throws ClassNotFoundException, SQLException{
    int userId = 0;
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet result = null;       
	PreparedStatement pstmt = null;

    try {
        //initialize the SQL connection 
    		  		
			conn = getMySQLConnection();
			String query = "SELECT user_id FROM users WHERE email =? ";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, email);
			
			result = pstmt.executeQuery();
			if (result.next()){
				userId= result.getInt(1);
			}            
			
        //catch the exception if any
    } catch (SQLException e) {
        e.printStackTrace();
        //close all the open connections
    } finally {
        if (result != null) {
            result.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    return userId;
}
	
public int getRoomIdFromName(String roomName) throws ClassNotFoundException, SQLException{
    int roomId = 0;
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet result = null;       
	PreparedStatement pstmt = null;

    try {
        //initialize the SQL connection 
    		  		
			conn = getMySQLConnection();
			String query = "SELECT room_id FROM rooms WHERE room_name =? ";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, roomName);
			
			result = pstmt.executeQuery();
			if (result.next()){
				roomId= result.getInt(1);
			}            
			
        //catch the exception if any
    } catch (SQLException e) {
        e.printStackTrace();
        //close all the open connections
    } finally {
        if (result != null) {
            result.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    return roomId;
}	


	
	/**
	 * 
	 * @return the first record of the table
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String first() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;

		// initialize the variables to contain the results

		String name = "";
		String surname = "";
		String address = "";
		String tel = "";

		try {
			// initialize the SQL connection
			conn = getMySQLConnection();
			stmt = (Statement) conn.createStatement();
			// set the query and execute it
			String query = "SELECT * FROM users ORDER BY ID ASC LIMIT 1";
			result = stmt.executeQuery(query);

			// get the results and put them in the variables
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(3);
				surname = result.getString(2);
				address = result.getString(4);
				tel = result.getString(5);

				String temp = name + " " + surname + " " + address + " " + tel;
				return temp;

			}
			// catch the exception if any
		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return "";

	}

	/**
	 * 
	 * @return the last record of the table 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String last() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;

		// initialize the variables to contain the results

		String name = "";
		String surname = "";
		String address = "";
		String tel = "";

		try {
			// initialize the SQL connection
			conn = getMySQLConnection();
			stmt = (Statement) conn.createStatement();
			// set the query and execute it
			String query = "SELECT * FROM users ORDER BY ID DESC LIMIT 1";
			result = stmt.executeQuery(query);

			// get the results and put them in the variables
			while (result.next()) {
				id = result.getInt(1);
				name = result.getString(3);
				surname = result.getString(2);
				address = result.getString(4);
				tel = result.getString(5);

				String temp = name + " " + surname + " " + address + " " + tel;
				return temp;

			}
			// catch the exception if any
		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return "";
	}
	/**
	 * 
	 * @return the next record of the table 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String next()throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;

		// initialize the variables to contain the results

		String name = "";
		String surname = "";
		String address = "";
		String tel = "";

		try {
			// initialize the SQL connection
			conn = getMySQLConnection();
			stmt = (Statement) conn.createStatement();
			// set the query and execute it
			String query = "SELECT * FROM users WHERE id >"+id+" ORDER BY id LIMIT 1";
			
			result = stmt.executeQuery(query);
			
			
			
			while (result.next()) {
				
				id = result.getInt(1);
				name = result.getString(3);
				surname = result.getString(2);
				address = result.getString(4);
				tel = result.getString(5);

				String temp = name + " " + surname + " " + address + " " + tel;
				return temp;
				

			}
					
			// catch the exception if any
		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return "";

	}
	/**
	 * 
	 * @return the previous record of the table 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String previous()throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		if(id==0)
			id=10000;
		// initialize the variables to contain the results

		String name = "";
		String surname = "";
		String address = "";
		String tel = "";

		try {
			// initialize the SQL connection
			conn = getMySQLConnection();
			stmt = (Statement) conn.createStatement();
			// set the query and execute it
			String query = "SELECT * FROM users WHERE id <"+id+" ORDER BY id DESC LIMIT 1";
			
			result = stmt.executeQuery(query);
			
			
			
			while (result.next()) {
				
				id = result.getInt(1);
				name = result.getString(3);
				surname = result.getString(2);
				address = result.getString(4);
				tel = result.getString(5);

				String temp = name + " " + surname + " " + address + " " + tel;
				return temp;
				

			}
					
			// catch the exception if any
		} catch (SQLException e) {
			e.printStackTrace();
			// close all the open connections
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return "";
	}
}
