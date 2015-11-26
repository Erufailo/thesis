package edu.ptuxiaki.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

import edu.ptuxiaki.frontend.Header;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Ptuxiaki_Hotel2997 implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
		RootPanel.get().addStyleName("body");
		
		
		RootPanel.get("header").add(Header.getInstance());

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
