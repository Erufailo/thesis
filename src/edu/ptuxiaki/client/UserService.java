package edu.ptuxiaki.client;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService{

	public void register(String name, String surname,String email,String password, String tel);
	
	public String login(String email, String password);
}
