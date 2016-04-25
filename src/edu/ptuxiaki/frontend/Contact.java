package edu.ptuxiaki.frontend;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class Contact extends Composite {

	
	// instance of the class
	static private Contact _instance = null;
	FlowPanel fp = new FlowPanel();

	public Contact() {
		initPage();
		initWidget(fp);

	}
	// Singleton for creating one instance of a page
	public static Contact getInstance() {
		if (_instance == null) {
			_instance = new Contact();
		}
		return _instance;
	}

	public void initPage() {

		Label label = new Label("Kalispera pws pane ta kefia?");
		
		
		
		fp.add(label);
		
		fp.addStyleName("mainContent");
	}
}
