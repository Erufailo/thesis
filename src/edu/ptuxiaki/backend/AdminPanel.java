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
	FlowPanel fp2= new FlowPanel();
	HorizontalPanel navPanel = new HorizontalPanel();

	public AdminPanel() {
		initPage();
		initWidget(fp2);

	}
	// Singleton for creating one instance of a page
	public static AdminPanel getInstance() {
		if (_instance == null) {
			_instance = new AdminPanel();
		}
		return _instance;
	}

	public void initPage() {

		Label label = new Label("Kalispera pws pane ta kefia?");
		Button button = new Button("asd");
		
		
		fp.add(label);
		navPanel.add(button);
		fp2.add(navPanel);
		
		fp2.addStyleName("adminHeader");
		fp.addStyleName("mainContent");
	}
}



