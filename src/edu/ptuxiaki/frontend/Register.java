package edu.ptuxiaki.frontend;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.ptuxiaki.client.UserService;
import edu.ptuxiaki.client.UserServiceAsync;


public class Register extends Composite {
	
	private static final UserServiceAsync userService = GWT.create(UserService.class);

	// instance of the class
	static private Register _instance = null;
	private FlowPanel fp = new FlowPanel();
	private Grid grid = new Grid(13, 2);
	
	private Label label;
	private Label nameLbl;
	private TextBox nameTxb;
	private Label surnameLbl;
	private TextBox surnameTxb;
	private Label emailLbl;
	private TextBox emailTxb;
	private Label passwordLbl;
	private PasswordTextBox passwordTxb;
	private Label repassLbl;
	private PasswordTextBox repassTxb;
	private Label telLbl;
	private TextBox telTxb;
	private Button register;
	private Button clear;

	public Register() {
		initPage();
		initWidget(fp);

	}

	// Singleton for creating one instance of a page
	public static Register getInstance() {
		if (_instance == null) {
			_instance = new Register();
		}
		return _instance;
	}

	public void initPage() {

		label = new Label("Please insert your information below:");

		nameLbl = new Label("Name : ");
		nameTxb = new TextBox();
		surnameLbl = new Label("Surname : ");
		surnameTxb = new TextBox();
		emailLbl = new Label("Email : ");
		emailTxb = new TextBox();
		passwordLbl = new Label("Password : ");
		passwordTxb = new PasswordTextBox();
		repassLbl = new Label("Re-enter password : ");
		repassTxb = new PasswordTextBox();
		telLbl = new Label("Telephone : ");
		telTxb = new TextBox();

		register = new Button("Register");
		clear = new Button("Clear Fields");

		register.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(checkFields()){
					
					 String password = passwordTxb.getText();/*(get password */
				    // String hash = BCrypt.hashpw(password, BCrypt.gensalt());//generate hash
				     
				     userService.register(nameTxb.getText(), surnameTxb.getText(), emailTxb.getText(), password, telTxb.getText(),
				    		 new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							System.out.println("ir8e");
							fp.clear();
							Label success = new Label("Registration Completed Successfully");
							fp.add(success);
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}
					});
					
				}
				

			}
		});
		
		
		clear.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				nameTxb.setText("");
				surnameTxb.setText(""); 
				emailTxb.setText("");
				passwordTxb.setText("");
				repassTxb.setText("");
				telTxb.setText("");				
			}
		});
		

		grid.setWidget(0, 0, nameLbl);
		grid.setWidget(0, 1, nameTxb);
		grid.setWidget(1, 0, surnameLbl);
		grid.setWidget(1, 1, surnameTxb);
		grid.setWidget(2, 0, emailLbl);
		grid.setWidget(2, 1, emailTxb);
		grid.setWidget(3, 0, passwordLbl);
		grid.setWidget(3, 1, passwordTxb);
		grid.setWidget(4, 0, repassLbl);
		grid.setWidget(4, 1, repassTxb);
		grid.setWidget(5, 0, telLbl);
		grid.setWidget(5, 1, telTxb);
		grid.setWidget(6, 0, register);
		grid.setWidget(6, 1, clear);

		grid.addStyleName("myGrid");

		fp.add(label);
		fp.add(grid);
		fp.addStyleName("mainContent");
	}

	public boolean checkFields() {
		if (nameTxb.getText().equals("") || surnameTxb.getText().equals("") || emailTxb.getText().equals("") || 
			passwordTxb.getText().equals("") || repassTxb.getText().equals("") || telTxb.getText().equals("")  ) {
			errorBox(true);
			return false;
		}
		if(!passwordTxb.getText().equals(repassTxb.getText())){
			errorBox(false);
			return false;
		}
		
		return true;
	}
	/**
	 * Generates a Dialogbox with error message. 
	 * @param type  true for null fields, false for password mismatch
	 */
	public void errorBox(Boolean type){
		final DialogBox dlBox = new DialogBox();
		HTML text;
		dlBox.setTitle("Error");
		dlBox.setText("Error");
		
		if(type == true){
			 text = new HTML("Please fill all of your data!<br>");
		}else{
			 text = new HTML("Passwords does not match!<br>");
		}
		Button close = new Button("OK");
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.setHeight("100");
		dialogVPanel.setWidth("300");
		dialogVPanel.setSpacing(10);
		
		dlBox.setAnimationEnabled(true);
		dialogVPanel.add(text);
		dialogVPanel.add(close);
		dlBox.setWidget(dialogVPanel);
		dlBox.center();
		dlBox.show();
		close.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				dlBox.hide();
				
			}
		});
		
	}
}
