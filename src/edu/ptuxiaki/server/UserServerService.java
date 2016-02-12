package edu.ptuxiaki.server;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	@Override
	public String login(String email, String password) {
		try {
			String dbHash= db.login(email);
			System.out.println(dbHash);
			boolean valid = BCrypt.checkpw(password, dbHash);
			
			if(valid){
				System.out.println("yes it is "+ email); //TODO SESSION COOKIE
				// create session and store userid
				HttpServletRequest request = this.getThreadLocalRequest();
				HttpSession session = request.getSession(true);
				session.setAttribute("UserID", 1);
				return session.getId();
			}else{
				System.out.println("paixtike malakia");
			}	
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
