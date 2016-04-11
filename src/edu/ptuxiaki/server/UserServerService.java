package edu.ptuxiaki.server;

import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.ptuxiaki.client.UserData;
import edu.ptuxiaki.client.UserService;

public class UserServerService extends RemoteServiceServlet implements UserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int x=0;
	Database db = new Database();
	@Override
	public void register(String name, String surname, String email, String password, String tel) {
		
		
		System.out.println(name + " "+ password);
		//generate hash
		String hash = BCrypt.hashpw(password, BCrypt.gensalt());
		
		System.out.println(name + " "+ hash);
		
		try {
			//register the user in database
			db.register(name, surname, email, hash, tel);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}
	@Override
	public UserData login(String email, String password) {
		try {
			String dbHash= db.login(email);
			System.out.println(dbHash);
			//check if the login is valid given the password and comparing it with the hash from db
			boolean valid = BCrypt.checkpw(password, dbHash);
			
			if(valid){
				System.out.println("yes it is "+ email); 
				// create session and store user
				UserData user = new UserData();
				HttpServletRequest request = this.getThreadLocalRequest();
				HttpSession session = request.getSession(true);
				UUID token = UUID.randomUUID();
				user.setsID(session.getId());
				user.setToken(token.toString());
				user.setEmail(email);
				session.setAttribute("user", user);
				
				
				return user;
			}else{
				System.out.println("paixtike malakia");
			}	
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String changeRole() {
		
		
		if(x++%2==0){
			System.out.println("user");
			return "user";
			
		}
		System.out.println("guest");
		return "guest";
	}
	@Override
	public UserData checkSessionWithServer(String sessionID, String token) {
		UserData user=null;
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession(true);
		user = (UserData)session.getAttribute("user");
		
		System.out.println("Server: "+user.getsID() + user.getToken());
		
		return user;
	}
	

}
