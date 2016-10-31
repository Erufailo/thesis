package edu.ptuxiaki.backend;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import edu.ptuxiaki.client.GuiRole;
import edu.ptuxiaki.client.UserService;
import edu.ptuxiaki.client.UserServiceAsync;
import edu.ptuxiaki.frontend.RoomGallery;

public class AdminHeader extends Composite {

	// instance of the class
	static private AdminHeader _instance = null;
	private static final UserServiceAsync userService = GWT.create(UserService.class);

	FlowPanel fp2 = new FlowPanel();
	HorizontalPanel navPanel = new HorizontalPanel();

	public AdminHeader() {
		initPage();
		initWidget(fp2);

	}

	// Singleton for creating one instance of a page
	public static AdminHeader getInstance() {
		if (_instance == null) {
			_instance = new AdminHeader();
		}
		return _instance;
	}

	public void initPage() {
		Button overview = new Button("Overview");
		Button users = new Button("Manage Users");
		Button newRoom = new Button("Manage Rooms");
		Button bookings = new Button("Manage Bookings");
		Button logout = new Button("Logout");
		HTML title = new HTML("<h1>Hotel Administration Area</h1>");
		
		overview.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("mainContent").clear();
				RootPanel.get("mainContent").add(AdminPanel.getInstance());
				
			}
		});
		
		users.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("mainContent").clear();
				RootPanel.get("mainContent").add(Users.getInstance());
				
			}
		});
		
		newRoom.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("mainContent").clear();
				RootPanel.get("mainContent").add(Rooms.getInstance());
			}
		});
		
		bookings.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("mainContent").clear();
				RootPanel.get("mainContent").add(Bookings.getInstance());
			}
		});
		
		logout.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				userService.logout(new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						GuiRole gui = new GuiRole();
						gui.setRole("guest");
						History.newItem("home");
						Window.Location.reload();
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
				
				

			}
		});
		
		navPanel.add(overview);
		navPanel.add(users);
		navPanel.add(newRoom);
		navPanel.add(bookings);
		navPanel.add(logout);
		
		navPanel.setSpacing(20);
		title.addStyleName("center");
		fp2.add(navPanel);
		fp2.add(title);

		fp2.addStyleName("adminHeader");

	}
}