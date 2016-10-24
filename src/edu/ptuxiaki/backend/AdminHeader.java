package edu.ptuxiaki.backend;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import edu.ptuxiaki.frontend.RoomGallery;

public class AdminHeader extends Composite {

	// instance of the class
	static private AdminHeader _instance = null;

	FlowPanel fp2 = new FlowPanel();
	HorizontalPanel navPanel = new HorizontalPanel();

	public AdminHeader() {
		initPage();
		initWidget(fp2);

	}

	// Singleton for creating one instance of a page
	public static AdminHeader getInstance() {
		if (_instance == null) {
			_instance = new AdminHeader();
		}
		return _instance;
	}

	public void initPage() {
		Button overview = new Button("Overview");
		Button users = new Button("Manage Users");
		Button newRoom = new Button("Manage Rooms");
		Button bookings = new Button("Manage Bookings");
		
		overview.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("mainContent").clear();
				RootPanel.get("mainContent").add(AdminPanel.getInstance());
				
			}
		});
		
		users.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("mainContent").clear();
				RootPanel.get("mainContent").add(Users.getInstance());
				
			}
		});
		
		newRoom.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("mainContent").clear();
				RootPanel.get("mainContent").add(Rooms.getInstance());
			}
		});
		
		bookings.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("mainContent").clear();
				RootPanel.get("mainContent").add(Bookings.getInstance());
			}
		});
		
		navPanel.add(overview);
		navPanel.add(users);
		navPanel.add(newRoom);
		navPanel.add(bookings);
		
		fp2.add(navPanel);

		fp2.addStyleName("adminHeader");

	}
}