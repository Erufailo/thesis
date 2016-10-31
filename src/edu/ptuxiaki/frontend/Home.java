package edu.ptuxiaki.frontend;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.ptuxiaki.client.GuiRole;
import edu.ptuxiaki.client.UserService;
import edu.ptuxiaki.client.UserServiceAsync;


public class Home extends Composite {

	private static final UserServiceAsync userService = GWT.create(UserService.class);
	// instance of the class
	static private Home _instance = null;
	FlowPanel fp = new FlowPanel();

	public Home() {
		initPage();
		initWidget(fp);

	}
	// Singleton for creating one instance of a page
	public static Home getInstance() {
		if (_instance == null) {
			_instance = new Home();
		}
		return _instance;
	}

	public void initPage() {

		HTML label = new HTML("<h1>Informatics Engineering Hotel</h1></br>");
		
		HTML label2 = new HTML("<p>Welcome to Informatics Engineering Hotel, we wish you a pleasant stay<p></br>"
				+ "To make a booking you must first login or register.");
		
		Image image = new Image("http://127.0.0.1:8888/hotelImages/homeimg.jpg");
		image.setHeight("300px");
		
		
		
		VerticalPanel vPanel = new VerticalPanel();
		
		vPanel.add(label);
		vPanel.add(label2);
		vPanel.add(image);
		vPanel.addStyleName("center");
		fp.add(vPanel);
		
		fp.addStyleName("mainContent");
	}
}
