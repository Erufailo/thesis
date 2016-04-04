package edu.ptuxiaki.frontend;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class Header extends Composite {

	// instance of the class
	static private Header _instance = null;
	FlowPanel fp = new FlowPanel();
	HorizontalPanel navPanel = new HorizontalPanel();

	public Header(String role) {
		initPage(role);
		initWidget(fp);

	}

	// Singleton for creating one instance of a page
	public static Header getInstance(String role) {
		if (_instance == null || !role.equals("guest")) {
			_instance = new Header(role);
		}
		if (_instance == null || !role.equals("user")) {
			_instance = new Header(role);
		}
		return _instance;
	}
	//show header for each role
	public void initPage(String role) {
		
		if(role.equals("guest")){
			//RootPanel.get("header").clear();

			Button home = new Button("Home");
			Button register = new Button("Register");
			Button login = new Button("Login");
			Button booking = new Button("Booking");
	
			home.addClickHandler(new ClickHandler() {
	
				@Override
				public void onClick(ClickEvent event) {
					History.newItem("home");
				}
			});
	
			register.addClickHandler(new ClickHandler() {
	
				@Override
				public void onClick(ClickEvent event) {
					History.newItem("register");
	
				}
			});
	
			login.addClickHandler(new ClickHandler() {
	
				@Override
				public void onClick(ClickEvent event) {
					History.newItem("login");
	
				}
			});
			booking.addClickHandler(new ClickHandler() {
	
				@Override
				public void onClick(ClickEvent event) {
					History.newItem("booking");
	
				}
			});
	
			navPanel.add(home);
			navPanel.add(register);
			navPanel.add(login);
			navPanel.add(booking);
			fp.add(navPanel);
			fp.addStyleName("header");
		}	
		if(role.equals("user")){
			//RootPanel.get("header").clear();
			
			Button home = new Button("Home");
			
			home.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					History.newItem("home");
				}
			});
			
			navPanel.add(home);
			fp.add(navPanel);
			fp.addStyleName("header");
			
		}
	}
	
	
	
	
	
}