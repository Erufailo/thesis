package edu.ptuxiaki.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class RoleCheck {
	final UserServiceAsync userService = GWT.create(UserService.class);
	private String role;
	public RoleCheck() {
		 String token = Cookies.getCookie("token");
		 String sessionID = Cookies.getCookie("sid");
		    if (sessionID == null || token == null)
		    {
		    	GWT.log("RoleCheck Role null Cookies: "+ role);
		    	this.role= "guest";
		    } else
		    {
		    	checkWithServer(sessionID,token);
		        
		       
		    }
		
		
	}

	
		
	
	public String getRole(){
		GWT.log("RoleCheck Role sto return: "+ role);
			return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	private void checkWithServer(String sessionID, String token) {
		 userService.checkSessionWithServer(sessionID, token, new AsyncCallback<UserData>() {
				
				@Override
				public void onSuccess(UserData result) {
					GWT.log("RoleCheck Role onSuccess: "+ result.getRole());
					if(result.getRole() != null){
						setRole((result.getRole()));
					}else{
						setRole("guest");
					}
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					GWT.log("RoleCheck Role onFailure: "+ role);
					setRole("guest");
					
				}
			});//checkWithServerIfSessionIdIsStillLegal(); 
		
}
}