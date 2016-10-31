package edu.ptuxiaki.backend;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import edu.ptuxiaki.client.BookingService;
import edu.ptuxiaki.client.BookingServiceAsync;
import edu.ptuxiaki.client.RoleCheck;
import edu.ptuxiaki.client.RoomData;
import edu.ptuxiaki.client.RoomService;
import edu.ptuxiaki.client.RoomServiceAsync;
import edu.ptuxiaki.client.UserData;
import edu.ptuxiaki.client.UserService;
import edu.ptuxiaki.client.UserServiceAsync;

public class Users extends Composite {

	// instance of the class
	final UserServiceAsync userService = GWT.create(UserService.class);
	final BookingServiceAsync bookingService = GWT.create(BookingService.class);
	final RoomServiceAsync roomService = GWT.create(RoomService.class);
	static private Users _instance = null;
	FlowPanel fp = new FlowPanel();
	VerticalPanel panel = new VerticalPanel();
	HorizontalPanel bottomPanel = new HorizontalPanel();
	ArrayList<UserData> USERS = new ArrayList<>();
	private String selection = null;
	private int roomId;
	RoleCheck role = new RoleCheck();
	boolean isAllowed;

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
		if(role.getRole().equals("admin")){
			isAllowed = true;
		}else{
			isAllowed = false;
		}
		final CellTable<UserData> table = createTable();
		HTML label = new HTML("<h1>Registered Users</h1>");
		
		Button add = new Button("Add User");
		Button edit = new Button("Edit User");
		final TextBox findTextBox = new TextBox();
		Button findButton = new Button("Find User");
		Button updateButton = new Button("Update Table");
		Button delete = new Button("Delete User");
		Button makeBooking = new Button("Make a Booking");

		add.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addUserPopup();
				
				//createTable();
				
			}
		});

		edit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (getSelection() != null) {
					userService.getSingleUser(getSelection(), new AsyncCallback<UserData>() {

						@Override
						public void onSuccess(UserData result) {
							
							editUserPopup(result);
							
							
						}

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub

						}
					});
				} else {
					errorBox("Please select user to edit!");
				}

			}
		});
		
		findButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (findTextBox.getText() != null) {
					userService.getSingleUser(findTextBox.getText(), new AsyncCallback<UserData>() {

						@Override
						public void onSuccess(UserData result) {
							if(result!=null){
							ArrayList<UserData> user = new ArrayList<>();
							//UserData data = new UserData(name,surname,email,tel,role);
							user.add(result);
							
							table.setRowData(user);
							}
						}

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub

						}
					});
				} else {
					errorBox("Please select user");
				}

			}
		});
				
			
		
		updateButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				userService.getAllUsers(new AsyncCallback<ArrayList<UserData>>() {

					@Override
					public void onSuccess(ArrayList<UserData> result) {
						// Push the data into the widget.
						
						
						
						table.setRowData(result);

					}

					@Override
					public void onFailure(Throwable caught) {
						GWT.log("error");

					}
				});
				
			}
		});
		
		delete.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				if(isAllowed){
				final String selectedEmail = getSelection();
				
				userService.adminDeleteUser(selectedEmail, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						errorBox("User "+selectedEmail+" deleted successfully");
						//createTable();
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						errorBox("Error deleting user "+selectedEmail);
						
					}
				});
				}else{
					errorBox("Only admin is permmited for this action");
				}
					
			}
		});
		
		makeBooking.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				newBookingPopup(getSelection());
				
			}
		});

		bottomPanel.add(add);
		bottomPanel.add(edit);
		bottomPanel.add(findTextBox);
		bottomPanel.add(findButton);
		bottomPanel.add(delete);
		bottomPanel.add(makeBooking);
		bottomPanel.add(updateButton);
		

		panel.setBorderWidth(1);
		panel.setWidth("500");
		panel.add(label);
		panel.add(table);
	
		panel.addStyleName("center");
		panel.add(bottomPanel);
		fp.add(panel);
		// fp.add(label);

		fp.addStyleName("adminMainContent");
	}
	
	
	

	public CellTable<UserData> createTable() {

		final CellTable<UserData> table = new CellTable<UserData>();
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
					GWT.log("You selected: " + selected.getEmail());
					setSelection(selected.getEmail());
				}
			}
		});

		userService.getAllUsers(new AsyncCallback<ArrayList<UserData>>() {

			@Override
			public void onSuccess(ArrayList<UserData> result) {
				// Push the data into the widget.
				table.setRowCount(result.size(), true);
				table.setRowData(0, result);
				
				

			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("error");

			}
		});

		// table.setRowData(0, USERS);

		return table;
	}
	
	
	public void newBookingPopup(String user){
		final UserData userData = new UserData();
		final RoomData roomData = new RoomData();
		final String email = user;
		final DialogBox dlBox = new DialogBox();
		final Label success = new Label();
		HTML text;
		dlBox.setTitle("Add User");
		dlBox.setText("Add a new User");
		VerticalPanel dialogVPanel = new VerticalPanel();
		HorizontalPanel hPanel = new HorizontalPanel();
		final ListBox rooms = new ListBox();
		rooms.setVisibleItemCount(1);
		text = new HTML("Please select a room!<br>");
		
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd-MM-yyyy");
	      final DateBox dateBox = new DateBox();
	      dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
	      
	      final  Label test = new Label("Check-in Date");
	      dateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				
				//test.setText(dateBox.getTextBox().getText());
				
			}
		});
	      final DateBox dateBox2 = new DateBox();
	      dateBox2.setFormat(new DateBox.DefaultFormat(dateFormat));
	      
	      final  Label test2 = new Label("Check-out Date");
	      dateBox2.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				
				//test2.setText(dateBox2.getTextBox().getText());
				
			}
		});
	      
	      
	      
	      
		
		
		Button checkRooms = new Button("Check Available Rooms");
		
		checkRooms.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				bookingService.getAvailableRooms(dateBox.getTextBox().getText(), dateBox2.getTextBox().getText(), new AsyncCallback<ArrayList<RoomData>>() {
					
					@Override
					public void onSuccess(ArrayList<RoomData> result) {
//						if(rooms.getItemCount()!=0){
//							for(int i=0; i<=rooms.getItemCount();i++){
//								rooms.removeItem(i);
//							}
//						}
						for(int i=0; i<result.size();i++){
							rooms.addItem(result.get(i).getRoomName());
						}
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
		
		
		Button booking = new Button("Complete Booking");
		
		booking.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
			
				
			
				roomService.getRoom(rooms.getSelectedItemText(),new AsyncCallback<RoomData>() {
					
					@Override
					public void onSuccess(RoomData result) {
						GWT.log("get Room "+result.getId());
						roomData.setId(result.getId());
						GWT.log("get Room2 "+roomData.getId());
						userService.getSingleUser(email, new AsyncCallback<UserData>() {
							
							@Override
							public void onSuccess(UserData result) {
								userData.setId(result.getId());
								
								bookingService.addBooking(dateBox.getTextBox().getText(), dateBox2.getTextBox().getText(), roomData.getId(), userData.getId(), new AsyncCallback<Void>() {
									
									@Override
									public void onSuccess(Void result) {
										success.setText("Booking added");
										
									}
									
									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										
									}
								});
								
							}
							
							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}
						});
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						
						
					}
				});
				
				
				
				GWT.log("get Room3 "+roomData.getId());
				final int x = roomData.getId();
				
				
			}
		});
		
		
		Button close = new Button("Close window");
		
		
		
		dialogVPanel.setHeight("600");
		dialogVPanel.setWidth("500");
		dialogVPanel.setSpacing(10);

		dlBox.setAnimationEnabled(true);

		
		dialogVPanel.add(test);
		dialogVPanel.add(dateBox);
		dialogVPanel.add(test2);
		dialogVPanel.add(dateBox2);
		dialogVPanel.add(checkRooms);
		dialogVPanel.add(text);

		dialogVPanel.add(rooms);
		
		dialogVPanel.add(success);

		hPanel.add(booking);
		
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
	

	public void addUserPopup() {
		final DialogBox dlBox = new DialogBox();
		final Label success = new Label();
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
					errorBox("Please fill all the fields!");
				}

				else if (!passwordTxb.getText().equals(repassTxb.getText())) {
					errorBox("Passwords do not match!");
				} else {
					String password = passwordTxb.getText();/* (get password */

					userService.adminRegister(nameTxb.getText(), surnameTxb.getText(), emailTxb.getText(), password,
							telTxb.getText(), listBox1.getSelectedItemText(), new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {

							success.setText("Registration Completed successfully");

						}

						@Override
						public void onFailure(Throwable caught) {
							success.setText("Error in registration ");


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

	public void editUserPopup(UserData user) {
		final DialogBox dlBox = new DialogBox();
		final Label success = new Label();
		HTML text;
		dlBox.setTitle("Edit User");
		dlBox.setText("Edit an existing User");
		VerticalPanel dialogVPanel = new VerticalPanel();
		HorizontalPanel hPanel = new HorizontalPanel();
		// Make a new list box, adding a few items to it.
		final ListBox listBox1 = new ListBox();
		listBox1.addItem("customer");// roles
		listBox1.addItem("personnel");
		listBox1.addItem("admin");
		listBox1.addItem("inactive");

		listBox1.setVisibleItemCount(1);

		text = new HTML("Please fill all the fields!<br>");

		Label nameLbl = new Label("Name : ");
		final TextBox nameTxb = new TextBox();
		nameTxb.setText(user.getName());
		Label surnameLbl = new Label("Surname : ");
		final TextBox surnameTxb = new TextBox();
		surnameTxb.setText(user.getSurname());
		Label emailLbl = new Label("Email : ");
		final TextBox emailTxb = new TextBox();
		emailTxb.setText(user.getEmail());
		Label telLbl = new Label("Telephone : ");
		final TextBox telTxb = new TextBox();
		telTxb.setText(user.getTel());
		Button register = new Button("Register");
		Button clear = new Button("Clear Fields");

		Button close = new Button("Close window");

		register.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (nameTxb.getText().equals("") || surnameTxb.getText().equals("") || emailTxb.getText().equals("")
						|| telTxb.getText().equals("")) {
					errorBox("Please fill all the fields!");
				}

				else {
					userService.adminEditUser(nameTxb.getText(), surnameTxb.getText(), emailTxb.getText(),
							telTxb.getText(), listBox1.getSelectedItemText(), new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							success.setText("User edited successfully");

						}

						@Override
						public void onFailure(Throwable caught) {
							success.setText("Error editing user");

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
	public void errorBox(String errorText) {
		final DialogBox dlBox = new DialogBox();
		// HTML text;
		dlBox.setTitle("Error");
		dlBox.setText("Error");

		HTML text = new HTML(errorText + "<br>");

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

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

}
