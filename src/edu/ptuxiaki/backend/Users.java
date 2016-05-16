package edu.ptuxiaki.backend;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class Users extends Composite {

	
	// instance of the class
	static private Users _instance = null;
	FlowPanel fp = new FlowPanel();
	
	HorizontalPanel navPanel = new HorizontalPanel();

	public Users() {
		initPage();
		initWidget(fp);

	}
	// Singleton for creating one instance of a page
	public static Users getInstance() {
		if (_instance == null) {
			_instance = new Users();
		}
		return _instance;
	}

	public void initPage() {

		Label label = new Label("Kalispera pws pane ta kefia? Users");
	
		
		
		fp.add(label);
		
		
		fp.addStyleName("adminMainContent");
	}
}
