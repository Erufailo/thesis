package edu.ptuxiaki.server;

import java.sql.SQLException;
import java.util.ArrayList;
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
	public void adminRegister(String name, String surname, String email, String password, String tel, String role) {
		
		
		System.out.println(name + " "+ password);
		//generate hash
		String hash = BCrypt.hashpw(password, BCrypt.gensalt());
		
		System.out.println(name + " "+ hash);
		
		try {
			//register the user in database
			db.addUserFromAdmin(name, surname, email, hash, tel, role);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}
	
	@Override
	public void adminEditUser(String name, String surname, String email, String tel, String role) {
		try {
			db.editUserFromAdmin(name, surname, email, tel, role);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void adminDeleteUser(String email) {
		try {
			db.deleteUser(email);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public ArrayList<UserData> getAllUsers() {
		ArrayList<UserData> users= null;
		try {
			 users = db.getAllUsers();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	@Override
	public UserData getSingleUser(String email) {
		UserData user=null;
		try {
			user =db.getUser(email);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
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
				UserData user = db.getUser(email);
				
				
				// create session and store user
			
				HttpServletRequest request = this.getThreadLocalRequest();
				HttpSession session = request.getSession(true);
				UUID token = UUID.randomUUID();
				user.setsID(session.getId());
				user.setToken(token.toString());
				user.setEmail(email);
				session.setAttribute("user", user);
				user.showAll();
				
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
			System.out.println("customer");
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
		if(!user.getsID().equals(sessionID)||!user.getToken().equals(token)){
			user=null;
		}
	//	System.out.println("Session Server :"+user.getsID() +" " +user.getToken());					
		return user;
	}
	@Override
	public void logout() {
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute("user");
		
	}


	

	

	
	

}
