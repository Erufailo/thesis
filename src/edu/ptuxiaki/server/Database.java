package edu.ptuxiaki.server;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


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
	public void register(String name, String surname, String email, String password, String tel)
			throws ClassNotFoundException, SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getMySQLConnection();
			String query = "insert into users(fname, surname, email, passHash, tel) values(?,?,?,?,?)";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, surname);
			pstmt.setString(3, email);
			pstmt.setString(4, password);
			pstmt.setString(5, tel);

			pstmt.executeUpdate();

			System.out.println("added");

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
	 * Deletes a Person from the Database given his name and surname
	 * 
	 * @param firstName
	 * @param lastName
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void deleteUser(String firstName, String lastName) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getMySQLConnection();
			String query = "DELETE FROM users WHERE FirstName =? AND LastName = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);

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
	/**
	 * Finds a single user in the database given his name and surname
	 * 
	 * @param firstName
	 * @param lastName
	 * @return the user name and surname as a String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String findUser(String firstName, String lastName) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			conn = getMySQLConnection();
			String query = "SELECT * FROM users WHERE FirstName =? AND LastName = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(query);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);

			result = pstmt.executeQuery();
			if (result.next())
				return (result.getString(2) + " " + result.getString(3));

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
		return firstName + " "+ lastName + " Not Found";
	}
	/**
	 * 
	 * 
	 * @return returns all the users listed in the database as an Arraylist<String>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<String> showAllPersons() throws ClassNotFoundException, SQLException {

        ArrayList<String> persons = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;

        //initialize the variables to contain the results
       
        String name = "";
        String surname  = "";
        String address = "";
        String tel = "";

        try {
            //initialize the SQL connection 
            conn = getMySQLConnection();
            stmt = (Statement) conn.createStatement();
            //set the query and execute it
            String query = "select * from users";
            result = stmt.executeQuery(query);

            //get the results and put them in the variables
            while (result.next()) {
                name = result.getString(3);
                surname = result.getString(2);
                address = result.getString(4);
                tel = result.getString(5);
                
                String temp = name +" "+surname+" "+address+" "+tel;
                persons.add(temp);
               
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
        return persons;
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
