package edu.ptuxiaki.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import edu.ptuxiaki.frontend.Header;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Ptuxiaki_Hotel2997 implements EntryPoint {
	final UserServiceAsync userService = GWT.create(UserService.class);
	//initiate userRoles for changing the view depending the logged person
	GuiRole userRoles = new GuiRole();

	@Override
	public void onModuleLoad() {
		
		
		//add the style from css
		RootPanel.get().addStyleName("body");
	
		//check if session is alive(if exists at all)
		
		 String token = Cookies.getCookie("token");
		 String sessionID = Cookies.getCookie("sid");
		    if (sessionID == null || token == null)
		    {
		    	userRoles.setRole("guest");
		    } else
		    {
		        checkWithServerIfSessionIdIsStillLegal(sessionID, token);
		    }
		//userRoles.setRole("user");
		//RootPanel.get("header").add(Header.getInstance("user"));

		//initialize a token 
		String initToken = History.getToken();
		
		//if no tokens active == first run --> load home.java
		if(initToken.length()== 0){
			History.newItem("home");
		}
		
		History.addValueChangeHandler(new MyHistoryListener());
		History.fireCurrentHistoryState();
		//check if session is alive(if exists at all)
//		 sessionID = Cookies.getCookie("sid"); 
//		System.out.println("Client:" + sessionID);
//		if ( sessionID != null ) {
//			userService.checkSessionWithServer("sessionID", "asd", new AsyncCallback<UserData>() {
//				
//				@Override
//				public void onSuccess(UserData result) {
//					System.out.println("SClient:" + sessionID);
//					UserData user = result;
//					System.out.println(user.getsID());
//					
//				}
//				
//				@Override
//				public void onFailure(Throwable caught) {
//					System.out.println("FClient:" + sessionID);
//					
//				}
//			});//checkWithServerIfSessionIdIsStillLegal(); 
//			if(true){
//				userRoles.setRole("user");
//			}
//			
//		}else{
//			userRoles.setRole("guest");
//		}
		
		
		
	}
	public void checkWithServerIfSessionIdIsStillLegal(String sessionID, String token){
		
		userService.checkSessionWithServer(sessionID, token, new AsyncCallback<UserData>() {
			
			@Override
			public void onSuccess(UserData result) {
				
				UserData user = result;
				
				userRoles.setRole("user");
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				userRoles.setRole("guest");
				
			}
		});//checkWithServerIfSessionIdIsStillLegal(); 
	}
	
}
