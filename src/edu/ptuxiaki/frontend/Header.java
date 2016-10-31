package edu.ptuxiaki.frontend;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import edu.ptuxiaki.client.GuiRole;
import edu.ptuxiaki.client.UserService;
import edu.ptuxiaki.client.UserServiceAsync;

public class Header extends Composite {
	private static final UserServiceAsync userService = GWT.create(UserService.class);

	// instance of the class
	static private Header _instance = null;
	FlowPanel fp = new FlowPanel();
	HorizontalPanel navPanel = new HorizontalPanel();
	//FlowPanel navPanel = new FlowPanel();
	Label welcomeMsg = new Label("Welcome to IE Hotel");

	public Header(String role) {
		initPage(role);
		initWidget(fp);

	}

	// Singleton for creating one instance of a page
	public static Header getInstance(String role) {
		GWT.log("1st: " + role);
		if (_instance == null || !role.equals("guest")) {
			_instance = new Header(role);
			GWT.log("prwto if");
		} else if (_instance == null || !role.equals("customer")) {
			_instance = new Header(role);
			GWT.log("deutero if");
		}
		GWT.log("Prin to return: " + role);
		return _instance;
		// if (_instance == null) {
		// _instance = new Header(role);
		// }
		// return _instance;
		// _instance = new Header(role);
		// }

		// if (_instance == null ){
		// if(role.equals("guest")){
		// _instance = new Header(role);
		// }
		// if(role.equals("customer")){
		// _instance = new Header(role);
		// }
		//
		// }else{
		// if(role.equals("guest")){
		// _instance = new Header(role);
		// }
		// if(role.equals("customer")){
		// _instance = new Header(role);
		// }
		//
		// }
		// return _instance;
	}

	// show header for each role
	public void initPage(String role) {
		
		welcomeMsg.addStyleName("labelHeader");
		navPanel.add(welcomeMsg);
		navPanel.addStyleName("headerButtons");
		
		navPanel.setSpacing(15);
		if (role.equals("guest")) {
			// RootPanel.get("header").clear();

			Button home = new Button("Home");
			Button login = new Button("Login / Register");
			Button roomGallery = new Button("Room Gallery");
			Button contact = new Button("Contact");

			home.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("home");
				}
			});

			login.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("login");

				}
			});
			
			roomGallery.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("gallery");

				}
			});
			contact.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("contact");

				}
			});
			
			
		
			navPanel.add(home);
			navPanel.add(login);
			navPanel.add(roomGallery);
			navPanel.add(contact);
			
			
			
			
			
			fp.add(navPanel);
			fp.addStyleName("header");
		} else if (role.equals("customer")) {
			// RootPanel.get("header").clear();

			Button home = new Button("Home");
			Button booking = new Button("Booking");
			Button roomGallery = new Button("Room Gallery");
			Button contact = new Button("Contact");
			Button profile = new Button("Profile");
			Button logout = new Button("Logout");
			

			home.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("home");
				}
			});
			booking.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("booking");

				}
			});
			
			booking.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("booking");

				}
			});
			roomGallery.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("gallery");

				}
			});
			
			contact.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("contact");

				}
			});
			
			profile.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					History.newItem("profile");
					
				}
			});
			
			logout.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					userService.logout(new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							GuiRole gui = new GuiRole();
							gui.setRole("guest");
							History.newItem("home");
							Window.Location.reload();
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}
					});
					
					

				}
			});

			navPanel.add(home);
			navPanel.add(booking);
			navPanel.add(roomGallery);
			navPanel.add(contact);
			navPanel.add(profile);
			navPanel.add(logout);
			
			fp.add(navPanel);
			fp.addStyleName("header");

		} else if (role.equals("admin")||role.equals("personnel")) {
			// RootPanel.get("header").clear();

			Button home = new Button("Home");
			Button adminPanel = new Button("Admin Panel");
			Button logout = new Button("Logout");
			home.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("home");
				}
			});
			adminPanel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("admin");

				}
			});
			logout.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					userService.logout(new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							GuiRole gui = new GuiRole();
							gui.setRole("guest");
							History.newItem("home");
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}
					});
				}
			});


			navPanel.add(home);
			navPanel.add(adminPanel);
			navPanel.add(logout);
			fp.add(navPanel);
			fp.addStyleName("header");

		} else if (role.equals("personnel")) {
			// RootPanel.get("header").clear();

			Button home = new Button("Home");
			Button personnelPanel = new Button("Personel Panel");
			Button logout = new Button("Logout");

			home.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("home");
				}
			});
			personnelPanel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					History.newItem("personnel");

				}
			});
			logout.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					userService.logout(new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							GuiRole gui = new GuiRole();
							gui.setRole("guest");
							History.newItem("home");
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}
					});
				}
			});


			navPanel.add(home);
			navPanel.add(personnelPanel);
			navPanel.add(logout);
			fp.add(navPanel);
			fp.addStyleName("header");

		}

	}

}