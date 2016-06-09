package edu.ptuxiaki.client;



import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService{

	public void register(String name, String surname,String email,String password, String tel);
	
	public void adminRegister(String name, String surname,String email,String password, String tel, String role);
	
	public void adminEditUser(String name, String surname, String email , String tel,String role);
	
	public void adminDeleteUser(String email);
	
	public ArrayList<UserData> getAllUsers();
	
	public UserData login(String email, String password);
	
	public String changeRole();
	
	public UserData checkSessionWithServer(String sessionID, String token);
	
	public void logout();
}
