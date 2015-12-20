package edu.ptuxiaki.server;

import java.sql.SQLException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.ptuxiaki.client.UserService;

public class UserServerService extends RemoteServiceServlet implements UserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	Database db = new Database();
	@Override
	public void register(String name, String surname, String email, String password, String tel) {
		
		
		System.out.println(name + " "+ password);
		
		String hash = BCrypt.hashpw(password, BCrypt.gensalt());//generate hash
		
		System.out.println(name + " "+ hash);
		
		try {
			db.register(name, surname, email, hash, tel);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}
	

}
