package edu.ptuxiaki.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.RootPanel;

import edu.ptuxiaki.frontend.Home;


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
		}/*
		if (event.getValue().equals("add")) {
			RootPanel.get().clear();
			RootPanel.get().add(Add.getInstance());
		}
		if (event.getValue().equals("delete")) {
			RootPanel.get().clear();
			RootPanel.get().add(Delete.getInstance());
		}
*/
	}

}