package edu.ptuxiaki.frontend;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;



public class RoomGallery extends Composite {

	
	// instance of the class
	static private RoomGallery _instance = null;
	FlowPanel fp = new FlowPanel();

	public RoomGallery() {
		initPage();
		initWidget(fp);

	}
	// Singleton for creating one instance of a page
	public static RoomGallery getInstance() {
		if (_instance == null) {
			_instance = new RoomGallery();
		}
		return _instance;
	}

	public void initPage() {

		Label label = new Label("Room Galleries ela pare pare");
		
		
		
		fp.add(label);
		
		fp.addStyleName("mainContent");
	}
}
