package edu.ptuxiaki.frontend;


import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

import edu.ptuxiaki.client.UserService;
import edu.ptuxiaki.client.UserServiceAsync;



public class Login  extends Composite {
	
   private static final UserServiceAsync userService = GWT.create(UserService.class);

	// instance of the class
	static private Login _instance = null;
	private FlowPanel fp = new FlowPanel();
	private Grid grid = new Grid(3, 2);
	
	private Label label;
	
	private Label emailLbl;
	private TextBox emailTxb;
	private Label passwordLbl;
	private PasswordTextBox passwordTxb;
	
	private Button login;
	

	public Login() {
		initPage();
		initWidget(fp);

	}

	// Singleton for creating one instance of a page
	public static Login getInstance() {
		if (_instance == null) {
			_instance = new Login();
		}
		return _instance;
	}

	public void initPage() {

		label = new Label("Please insert your information below:");

		
		emailLbl = new Label("Email : ");
		emailTxb = new TextBox();
		passwordLbl = new Label("Password : ");
		passwordTxb = new PasswordTextBox();
	

		login = new Button("Login");
	
		login.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				userService.login(emailTxb.getText(), passwordTxb.getText(), new AsyncCallback<String>() {
					
					@Override
					public void onSuccess(String result) {
						fp.remove(grid);
						fp.add(new Label(result));
						String sessionID = result;
                        final long DURATION = 1000 * 60 * 60 * 24 * 1;
                        Date expires = new Date(System.currentTimeMillis() + DURATION);
                        Cookies.setCookie("sid", sessionID, expires, null, "/", false);
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
				
			}
		});
		
		

		grid.setWidget(0, 0, emailLbl);
		grid.setWidget(0, 1, emailTxb);
		grid.setWidget(1, 0, passwordLbl);
		grid.setWidget(1, 1, passwordTxb);
		grid.setWidget(2, 0, login);
	
	

		grid.addStyleName("myGrid");

		fp.add(label);
		fp.add(grid);
		fp.addStyleName("mainContent");
		
	}
}