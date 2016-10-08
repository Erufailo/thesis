package edu.ptuxiaki.frontend;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

import edu.ptuxiaki.client.GuiRole;
import edu.ptuxiaki.client.RoleCheck;
import edu.ptuxiaki.client.UserData;
import edu.ptuxiaki.client.UserService;
import edu.ptuxiaki.client.UserServiceAsync;

public class Login extends Composite {

	private static final UserServiceAsync userService = GWT.create(UserService.class);

	// instance of the class
	static private Login _instance = null;
	static RoleCheck roleCheck = new RoleCheck();
	GuiRole userRoles = new GuiRole();
	private FlowPanel fp = new FlowPanel();
	private Grid grid = new Grid(3, 2);

	private Label label;

	private Label emailLbl;
	private TextBox emailTxb;
	private Label passwordLbl;
	private PasswordTextBox passwordTxb;

	private Button login;
	private Button register;

	public Login() {

		initPage();
		initWidget(fp);

	}

	// Singleton for creating one instance of a page
	public static Login getInstance() {
		if (!roleCheck.getRole().equals("guest")) {
			_instance=null;
			History.newItem("home");
		} else {
			if (_instance == null) {
				_instance = new Login();
			}

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
		register = new Button("Register");

		login.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				userService.login(emailTxb.getText(), passwordTxb.getText(), new AsyncCallback<UserData>() {

					@Override
					public void onSuccess(UserData result) {
						fp.remove(grid);
						fp.add(new Label(result.getEmail()));
						fp.add(new Label(result.getToken()));
						fp.add(new Label(result.getsID()));
						// create cookie based on the session with server
						String sessionID = result.getsID();
						String token = result.getToken();
						final long DURATION = 1000 * 60 * 60 * 24 * 1;
						Date expires = new Date(System.currentTimeMillis() + DURATION);
						Cookies.setCookie("sid", sessionID, expires, null, "/", false);//new Date(System.currentTimeMillis()+36000000)
						Cookies.setCookie("token", token, expires, null, "/", false);
						userRoles.setRole(result.getRole());

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});

			}
		});

		register.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				History.newItem("register");

			}
		});

		grid.setWidget(0, 0, emailLbl);
		grid.setWidget(0, 1, emailTxb);
		grid.setWidget(1, 0, passwordLbl);
		grid.setWidget(1, 1, passwordTxb);
		grid.setWidget(2, 0, login);
		grid.setWidget(2, 1, register);

		grid.addStyleName("myGrid");

		fp.add(label);
		fp.add(grid);
		fp.addStyleName("mainContent");

	}
}