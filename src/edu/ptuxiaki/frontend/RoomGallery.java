package edu.ptuxiaki.frontend;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
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
		 // Create a grid
	      Grid grid = new Grid(3, 3);

	      // Add images to the grid
	      int numRows = grid.getRowCount();
	      int numColumns = grid.getColumnCount();
	      int x=1;
	      for (int row = 0; row < numRows; row++) {
	         for (int col = 0; col < numColumns; col++) {
	        	 Image image = new Image("http://127.0.0.1:8888/hotelImages/"+ x++ +".jpg");
	        	 
	        	 image.setWidth("197px");

	        	// image.setUrl("http://127.0.0.1:8888/images/accounts.png");
	        	 
	        	 
	        	 grid.setWidget(row, col, image);
	            image.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						GWT.log( " asdasd");
						
					}
				});
	            
	            
	         }
	      }

	     
	   
		
		
		fp.add(grid);
		grid.addStyleName("center");
		fp.addStyleName("mainContent");
	}
}
