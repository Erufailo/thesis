package edu.ptuxiaki.frontend;


import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;



public class Login  extends Composite {
	
//	private static final UserServiceAsync userService = GWT.create(UserService.class);

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