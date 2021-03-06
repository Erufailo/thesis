package edu.ptuxiaki.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;


import edu.ptuxiaki.backend.AdminHeader;
import edu.ptuxiaki.backend.AdminPanel;
import edu.ptuxiaki.frontend.Booking;
import edu.ptuxiaki.frontend.Contact;
import edu.ptuxiaki.frontend.Home;
import edu.ptuxiaki.frontend.Login;
import edu.ptuxiaki.frontend.Profile;
import edu.ptuxiaki.frontend.Register;
import edu.ptuxiaki.frontend.RoomGallery;
import edu.ptuxiaki.frontend.Success;


/**
 * 
 * History Handler for keeping and changing pages
 * with Browser history
 *
 */
public class MyHistoryListener implements ValueChangeHandler<String> {

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		System.out.println("Current State : " + event.getValue());

		if (event.getValue().equals("home")) {
			RootPanel.get("mainContent").clear();
			RootPanel.get("mainContent").add(Home.getInstance());
		}
		if (event.getValue().equals("register")) {
			RootPanel.get("mainContent").clear();
			RootPanel.get("mainContent").add(Register.getInstance());
		}
		if (event.getValue().equals("login")) {
			RootPanel.get("mainContent").clear();
			RootPanel.get("mainContent").add(Login.getInstance());
		}
		if (event.getValue().equals("booking")) {
			RootPanel.get("mainContent").clear();
			RootPanel.get("mainContent").add(Booking.getInstance());
		}
		if (event.getValue().equals("gallery")) {
			RootPanel.get("mainContent").clear();
			RootPanel.get("mainContent").add(RoomGallery.getInstance());
		}
		if (event.getValue().equals("contact")) {
			RootPanel.get("mainContent").clear();
			RootPanel.get("mainContent").add(Contact.getInstance());
		}
		if (event.getValue().equals("success")) {
			RootPanel.get("mainContent").clear();
			RootPanel.get("mainContent").add(Success.getInstance());
		}
		if (event.getValue().equals("profile")) {
			RootPanel.get("mainContent").clear();
			RootPanel.get("mainContent").add(Profile.getInstance());
		}
		if (event.getValue().equals("admin")) {
			RootPanel.get("mainContent").clear();
			RootPanel.get("header").clear();
			RootPanel.get("footer").clear();
			RootPanel.get().clear();
			RootPanel.get("body").removeStyleName("body");
			RootPanel.get().addStyleName("adminBody");
			RootPanel.get("header").add(AdminHeader.getInstance());
			RootPanel.get("mainContent").add(AdminPanel.getInstance());
			RootPanel.get("headerText").clear();
		}

	}

}