package edu.ptuxiaki.frontend;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;


public class Home extends Composite {

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

		Label label = new Label("Initialization of Project");
			
		
		
		fp.add(label);
		fp.addStyleName("mainContent");
	}
}
