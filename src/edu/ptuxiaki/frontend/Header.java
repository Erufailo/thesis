package edu.ptuxiaki.frontend;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;


public class Header extends Composite {

	// instance of the class
	static private Header _instance = null;
	FlowPanel fp = new FlowPanel();
	HorizontalPanel navPanel = new HorizontalPanel();

	public Header() {
		initPage();
		initWidget(fp);

	}
	// Singleton for creating one instance of a page
	public static Header getInstance() {
		if (_instance == null) {
			_instance = new Header();
		}
		return _instance;
	}

	public void initPage() {

		Button home = new Button("Home");
		Button register= new Button("Register");
		Button login= new Button("Login");
		
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
			
		
		navPanel.add(home);
		navPanel.add(register);
		navPanel.add(login);
		fp.add(navPanel);
		fp.addStyleName("header");
	}
}