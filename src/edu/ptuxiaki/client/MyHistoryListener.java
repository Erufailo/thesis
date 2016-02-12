package edu.ptuxiaki.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.RootPanel;

import edu.ptuxiaki.frontend.Booking;
import edu.ptuxiaki.frontend.Home;
import edu.ptuxiaki.frontend.Login;
import edu.ptuxiaki.frontend.Register;


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

	}

}