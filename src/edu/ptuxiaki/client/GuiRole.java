package edu.ptuxiaki.client;


import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import edu.ptuxiaki.frontend.Header;

public class GuiRole {
	RoleCheck roleCheck = new RoleCheck();
	
	public GuiRole() {
		// TODO Auto-generated constructor stub
	}
	
	
	//change the GUI with the appropriate role for the user(customer. manager etc)
	public void setRole(String role){
		GWT.log("GUIRole: "+ role);
		
		RootPanel.get("header").clear();
		RootPanel.get("header").add(Header.getInstance(role));	
	}
	
	

}
