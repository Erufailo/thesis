package edu.ptuxiaki.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

import edu.ptuxiaki.frontend.Header;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Ptuxiaki_Hotel2997 implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
		//initiate userRoles for changing the view depending the logged person
		GuiRole userRoles = new GuiRole();
		
		//add the style from css
		RootPanel.get().addStyleName("body");
		
		
		//check if session is alive(if exists at all)
		String sessionID = Cookies.getCookie("sid"); 
		if ( sessionID != null ) {
			//checkWithServerIfSessionIdIsStillLegal(); 
			if(true){
				userRoles.setRole("user");
			}
			
		}else{
			userRoles.setRole("guest");
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
		
		
		
		
	}
	
	
}
