package edu.ptuxiaki.frontend;

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
		
			
		
		navPanel.add(home);
		fp.add(navPanel);
		fp.addStyleName("header");
	}
}