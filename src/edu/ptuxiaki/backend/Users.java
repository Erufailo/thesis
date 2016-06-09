package edu.ptuxiaki.backend;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import edu.ptuxiaki.client.UserData;
import edu.ptuxiaki.client.UserService;
import edu.ptuxiaki.client.UserServiceAsync;

public class Users extends Composite {

	// instance of the class
	final UserServiceAsync userService = GWT.create(UserService.class);
	static private Users _instance = null;
	FlowPanel fp = new FlowPanel();
	VerticalPanel panel = new VerticalPanel();
	HorizontalPanel bottomPanel = new HorizontalPanel();
	ArrayList<UserData> USERS = new ArrayList<>();

	public Users() {
		initPage();
		initWidget(fp);

	}

	// Singleton for creating one instance of a page
	public static Users getInstance() {
		if (_instance == null) {
			_instance = new Users();
		}
		return _instance;
	}

	public void initPage() {

		CellTable<UserData> table = createTable();

		Button add = new Button("Add User");
		Button edit = new Button("Edit User");
		TextBox findTextBox = new TextBox();
		Button findButton = new Button("Find User");
		Button delete = new Button("Delete User");

		add.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addUserPopup();

			}
		});

		bottomPanel.add(add);
		bottomPanel.add(edit);
		bottomPanel.add(findTextBox);
		bottomPanel.add(findButton);
		bottomPanel.add(delete);

		panel.setBorderWidth(1);
		panel.setWidth("500");
		panel.add(table);
		panel.add(bottomPanel);
		fp.add(panel);
		// fp.add(label);

		fp.addStyleName("adminMainContent");
	}

	public CellTable<UserData> createTable() {
//		
//		userService.getAllUsers(new AsyncCallback<ArrayList<UserData>>() {
//			
//			@Override
//			public void onSuccess(ArrayList<UserData> result) {
//				//ArrayList<UserData> clone = (ArrayList<UserData>)result.clone();
//				//USERS = clone;
//				USERS = new ArrayList<UserData>(result);
//				GWT.log("asd: "+result.size());
//				
//			}
//			
//			@Override
//			public void onFailure(Throwable caught) {
//				GWT.log("error");
//				
//			}
//		});
		USERS.add(new UserData("John", "Rin", "123 Fourth Avenue", "asd", "asd"));
		USERS.add(new UserData("Agapi", "Mono", "123 Fourth Fifth", "asd", "asd"));
		USERS.add(new UserData("Maxo", "Mathe", "Pro", "asd", "asd"));

		CellTable<UserData> table = new CellTable<UserData>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a text column to show the name.
		TextColumn<UserData> nameColumn = new TextColumn<UserData>() {
			@Override
			public String getValue(UserData object) {
				return object.getName();
			}
		};
		table.addColumn(nameColumn, "Name");

		TextColumn<UserData> lastNameColumn = new TextColumn<UserData>() {
			@Override
			public String getValue(UserData object) {
				return object.getSurname();
			}
		};
		table.addColumn(lastNameColumn, "Last name");

		TextColumn<UserData> emailColumn = new TextColumn<UserData>() {
			@Override
			public String getValue(UserData object) {
				return object.getEmail();
			}
		};
		table.addColumn(emailColumn, "Email");

		TextColumn<UserData> telephoneColumn = new TextColumn<UserData>() {
			@Override
			public String getValue(UserData object) {
				return object.getTel();
			}
		};
		table.addColumn(telephoneColumn, "Telephone");

		TextColumn<UserData> roleColumn = new TextColumn<UserData>() {
			@Override
			public String getValue(UserData object) {
				return object.getRole();
			}
		};
		table.addColumn(roleColumn, "Role");

		// Add a selection model to handle user selection.
		final SingleSelectionModel<UserData> selectionModel = new SingleSelectionModel<UserData>();
		table.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				UserData selected = selectionModel.getSelectedObject();
				if (selected != null) {
					GWT.log("You selected: " + selected.getName());
				}
			}
		});

		// Set the total row count. This isn't strictly necessary,
		// but it affects paging calculations, so its good habit to
		// keep the row count up to date.
		table.setRowCount(USERS.size(), true);
		GWT.log(""+USERS.size());

		// Push the data into the widget.
		table.setRowData(0, USERS);

		return table;
	}

	public void addUserPopup() {
		final DialogBox dlBox = new DialogBox();
		final Label success=new Label();
		HTML text;
		dlBox.setTitle("Add User");
		dlBox.setText("Add a new User");
		VerticalPanel dialogVPanel = new VerticalPanel();
		HorizontalPanel hPanel = new HorizontalPanel();
		// Make a new list box, adding a few items to it.
		final ListBox listBox1 = new ListBox();
		listBox1.addItem("customer");// roles
		listBox1.addItem("personnel");
		listBox1.addItem("admin");
		listBox1.addItem("inactive");

		listBox1.setVisibleItemCount(1);

		text = new HTML("Please fill all of your data!<br>");

		Label nameLbl = new Label("Name : ");
		final TextBox nameTxb = new TextBox();
		Label surnameLbl = new Label("Surname : ");
		final TextBox surnameTxb = new TextBox();
		Label emailLbl = new Label("Email : ");
		final TextBox emailTxb = new TextBox();
		Label passwordLbl = new Label("Password : ");
		final PasswordTextBox passwordTxb = new PasswordTextBox();
		Label repassLbl = new Label("Re-enter password : ");
		final PasswordTextBox repassTxb = new PasswordTextBox();
		Label telLbl = new Label("Telephone : ");
		final TextBox telTxb = new TextBox();
		Button register = new Button("Register");
		Button clear = new Button("Clear Fields");

		Button close = new Button("Close window");

		register.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (nameTxb.getText().equals("") || surnameTxb.getText().equals("") || emailTxb.getText().equals("")
						|| passwordTxb.getText().equals("") || repassTxb.getText().equals("")
						|| telTxb.getText().equals("")) {
					errorBox(true);
				}

				else if (!passwordTxb.getText().equals(repassTxb.getText())) {
					errorBox(false);
				} else {
					String password = passwordTxb.getText();/* (get password */

					userService.adminRegister(nameTxb.getText(), surnameTxb.getText(), emailTxb.getText(), password,
							telTxb.getText(),listBox1.getSelectedItemText(), new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
						
							success.setText("Registration Completed successfully");

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

		dialogVPanel.setHeight("600");
		dialogVPanel.setWidth("500");
		dialogVPanel.setSpacing(10);

		dlBox.setAnimationEnabled(true);

		dialogVPanel.add(text);

		dialogVPanel.add(nameLbl);
		dialogVPanel.add(nameTxb);
		dialogVPanel.add(surnameLbl);
		dialogVPanel.add(surnameTxb);
		dialogVPanel.add(emailLbl);
		dialogVPanel.add(emailTxb);
		dialogVPanel.add(passwordLbl);
		dialogVPanel.add(passwordTxb);
		dialogVPanel.add(repassLbl);
		dialogVPanel.add(repassTxb);
		dialogVPanel.add(telLbl);
		dialogVPanel.add(telTxb);
		dialogVPanel.add(listBox1);
		dialogVPanel.add(success);
		
		hPanel.add(register);
		hPanel.add(clear);
		hPanel.add(close);
		dialogVPanel.add(hPanel);

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

	/**
	 * Generates a Dialogbox with error message.
	 * 
	 * @param type
	 *            true for null fields, false for password mismatch
	 */
	public void errorBox(Boolean type) {
		final DialogBox dlBox = new DialogBox();
		HTML text;
		dlBox.setTitle("Error");
		dlBox.setText("Error");

		if (type == true) {
			text = new HTML("Please fill all of your data!<br>");
		} else {
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
