package edu.ptuxiaki.backend;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;



public class AdminPanel extends Composite {

	
	// instance of the class
	static private AdminPanel _instance = null;
	FlowPanel fp = new FlowPanel();
	
	HorizontalPanel navPanel = new HorizontalPanel();

	public AdminPanel() {
		initPage();
		initWidget(fp);

	}
	// Singleton for creating one instance of a page
	public static AdminPanel getInstance() {
		if (_instance == null) {
			_instance = new AdminPanel();
		}
		return _instance;
	}

	public void initPage() {

		Label label = new Label("Kalispera pws pane ta kefia? Admin");
	
		
		
		fp.add(label);
		
		
		fp.addStyleName("adminMainContent");
	}
}



