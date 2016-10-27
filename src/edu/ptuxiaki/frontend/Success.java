package edu.ptuxiaki.frontend;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

import edu.ptuxiaki.client.UserService;
import edu.ptuxiaki.client.UserServiceAsync;

public class Success extends Composite{

	//private static final UserServiceAsync userService = GWT.create(UserService.class);
	// instance of the class
	static private Success _instance = null;
	FlowPanel fp = new FlowPanel();

	public Success() {
		initPage();
		initWidget(fp);

	}
	// Singleton for creating one instance of a page
	public static Success getInstance() {
		if (_instance == null) {
			_instance = new Success();
		}
		return _instance;
	}

	public void initPage() {
		
		Label test = new Label("Your Booking is Complete, thank you for choosing us!");
		
		fp.add(test);
	    fp.addStyleName("mainContent");
		_instance= this;
	}
}
