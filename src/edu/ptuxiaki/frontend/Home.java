package edu.ptuxiaki.frontend;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

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

		Label label = new Label("Initialization of Project");
		
		Button testGui = new Button("Change Header");
			
		testGui.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				userService.changeRole(new AsyncCallback<String>() {
					
					@Override
					public void onSuccess(String result) {
						GuiRole role = new GuiRole();
						role.setRole(result);
						
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
				
			}
		});
		
		fp.add(label);
		fp.add(testGui);
		fp.addStyleName("mainContent");
	}
}
