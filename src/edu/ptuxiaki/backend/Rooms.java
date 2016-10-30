package edu.ptuxiaki.backend;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
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

import edu.ptuxiaki.client.RoomData;
import edu.ptuxiaki.client.RoomService;
import edu.ptuxiaki.client.RoomServiceAsync;
import edu.ptuxiaki.client.UserData;
import edu.ptuxiaki.client.UserService;
import edu.ptuxiaki.client.UserServiceAsync;

public class Rooms  extends Composite {

	
	// instance of the class
	final RoomServiceAsync roomService = GWT.create(RoomService.class);
	static private Rooms _instance = null;
	FlowPanel fp = new FlowPanel();
	
	HorizontalPanel navPanel = new HorizontalPanel();
	
	VerticalPanel panel = new VerticalPanel();
	HorizontalPanel bottomPanel = new HorizontalPanel();
	//ArrayList<UserData> USERS = new ArrayList<>();
	
	private String selection = null;

	public Rooms() {
		initPage();
		initWidget(fp);

	}
	// Singleton for creating one instance of a page
	public static Rooms getInstance() {
		if (_instance == null) {
			_instance = new Rooms();
		}
		return _instance;
	}

	public void initPage() {


		final CellTable<RoomData> table = createTable();
		
		Button add = new Button("Add User");
		Button edit = new Button("Edit User");
		final TextBox findTextBox = new TextBox();
		Button findButton = new Button("Find User");
		Button updateButton = new Button("Update Table");
		Button delete = new Button("Delete User");
		
		add.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				addRoomPopup();
				
			}
		});
		edit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				roomService.getRoom(getSelection(), new AsyncCallback<RoomData>() {
					
					@Override
					public void onSuccess(RoomData result) {
						editRoomPopup(result);
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
				
			}
		});
		
		
		findButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (findTextBox.getText() != null) {
					roomService.getRoom(findTextBox.getText(), new AsyncCallback<RoomData>() {

						@Override
						public void onSuccess(RoomData result) {
							if(result!=null){
							ArrayList<RoomData> user = new ArrayList<>();
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
				roomService.getAllRooms(new AsyncCallback<ArrayList<RoomData>>() {

					@Override
					public void onSuccess(ArrayList<RoomData> result) {
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
					roomService.deleteRoom(getSelection(), new AsyncCallback<Void>() {
						
						@Override
						public void onSuccess(Void result) {
							errorBox("Room "+getSelection()+" deleted successfully");
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							errorBox("Error deleting room "+getSelection());
							
						}
					});
					
				}
			});
		
		bottomPanel.add(add);
		bottomPanel.add(edit);
		bottomPanel.add(findTextBox);
		bottomPanel.add(findButton);
		bottomPanel.add(delete);
		bottomPanel.add(updateButton);

		panel.setBorderWidth(1);
		panel.setWidth("500");
		panel.add(table);
		panel.addStyleName("center");
		panel.add(bottomPanel);
		fp.add(panel);
		// fp.add(label);

		fp.addStyleName("adminMainContent");
	}
	
	
	
	public CellTable<RoomData> createTable() {

		final CellTable<RoomData> table = new CellTable<RoomData>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a text column to show the name.
		TextColumn<RoomData> nameColumn = new TextColumn<RoomData>() {
			@Override
			public String getValue(RoomData object) {
				return object.getRoomName();
			}
		};
		table.addColumn(nameColumn, "Room Name");

		TextColumn<RoomData> capacityColumn = new TextColumn<RoomData>() {
			@Override
			public String getValue(RoomData object) {
				return object.getCapacity()+"";
			}
		};
		table.addColumn(capacityColumn, "Capacity");

		TextColumn<RoomData> descriptionColumn = new TextColumn<RoomData>() {
			@Override
			public String getValue(RoomData object) {
				return object.getDescription();
			}
		};
		table.addColumn(descriptionColumn, "Description");

		TextColumn<RoomData> availableColumn = new TextColumn<RoomData>() {
			@Override
			public String getValue(RoomData object) {
				return object.getAvailable()+"";
			}
		};
		table.addColumn(availableColumn, "Available");

		TextColumn<RoomData> priceColumn = new TextColumn<RoomData>() {
			@Override
			public String getValue(RoomData object) {
				return object.getPrice();
			}
		};
		table.addColumn(priceColumn, "Price");

		// Add a selection model to handle user selection.
		final SingleSelectionModel<RoomData> selectionModel = new SingleSelectionModel<RoomData>();
		table.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				RoomData selected = selectionModel.getSelectedObject();
				if (selected != null) {
					GWT.log("You selected: " + selected.getRoomName());
					setSelection(selected.getRoomName());
				}
			}
		});
		roomService.getAllRooms(new AsyncCallback<ArrayList<RoomData>>() {
			
			@Override
			public void onSuccess(ArrayList<RoomData> result) {
				 //Push the data into the widget.
				table.setRowCount(result.size(), true);
				table.setRowData(0, result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("error");
				
			}
		});
//		userService.getAllUsers(new AsyncCallback<ArrayList<UserData>>() {
//
//			@Override
//			public void onSuccess(ArrayList<UserData> result) {
//				// Push the data into the widget.
//				table.setRowCount(result.size(), true);
//				table.setRowData(0, result);
//				
//				
//
//			}
//
//			@Override
//			public void onFailure(Throwable caught) {
//				GWT.log("error");
//
//			}
//		});

		// table.setRowData(0, USERS);

		return table;
	}
	
	public void addRoomPopup() {
		final DialogBox dlBox = new DialogBox();
		final Label success = new Label();
		HTML text;
		dlBox.setTitle("Add User");
		dlBox.setText("Add a new User");
		VerticalPanel dialogVPanel = new VerticalPanel();
		HorizontalPanel hPanel = new HorizontalPanel();
		// Make a new list box, adding a few items to it.
		final ListBox listBox1 = new ListBox();
		listBox1.addItem("Available");// available room
		listBox1.addItem("Not Available");
		

		listBox1.setVisibleItemCount(1);

		text = new HTML("Please fill all of the room data!<br>");

		Label nameLbl = new Label("Room Name : ");
		final TextBox nameTxb = new TextBox();
		Label capacityLbl = new Label("Capacity : ");
		final TextBox capacityTxb = new TextBox();
		Label descriptionLbl = new Label("Description : ");
		final TextBox descriptionTxb = new TextBox();
		Label availableLbl = new Label("Available : ");
		
		Label priceLbl = new Label("Price : ");
		final TextBox priceTxb = new TextBox(); //continue from here
		Button addRoom = new Button("Add Room");
		Button clear = new Button("Clear Fields");

		Button close = new Button("Close window");

		addRoom.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (nameTxb.getText().equals("") || capacityTxb.getText().equals("") || descriptionTxb.getText().equals("")
						|| priceTxb.getText().equals("") ) {
					errorBox("Please fill all the fields!");
				}

				else {
					int roomAvailable;
					String temp = listBox1.getSelectedItemText();
					if(temp=="Not Available"){
						roomAvailable = 0;
					}else
						roomAvailable = 1;
					
					
					roomService.addRoomFromAdmin(nameTxb.getText(), Integer.parseInt(capacityTxb.getText()), descriptionTxb.getText(), roomAvailable,
							priceTxb.getText(), new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {

							success.setText("Room added successfully");

						}

						@Override
						public void onFailure(Throwable caught) {
							success.setText("Error in adding the room ");


						}
					});

				}

			}
		});

		clear.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				nameTxb.setText("");
				capacityTxb.setText("");
				descriptionTxb.setText("");
				priceTxb.setText("");
				
			}
		});

		dialogVPanel.setHeight("600");
		dialogVPanel.setWidth("500");
		dialogVPanel.setSpacing(10);

		dlBox.setAnimationEnabled(true);

		dialogVPanel.add(text);

		dialogVPanel.add(nameLbl);
		dialogVPanel.add(nameTxb);
		dialogVPanel.add(capacityLbl);
		dialogVPanel.add(capacityTxb);
		dialogVPanel.add(descriptionLbl);
		dialogVPanel.add(descriptionTxb);
		dialogVPanel.add(availableLbl);
		dialogVPanel.add(listBox1);
		dialogVPanel.add(priceLbl);
		dialogVPanel.add(priceTxb);
		
		
		dialogVPanel.add(success);

		hPanel.add(addRoom);
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
	
	
	public void editRoomPopup(RoomData room) {
		final DialogBox dlBox = new DialogBox();
		final Label success = new Label();
		HTML text;
		dlBox.setTitle("Edit a Room");
		dlBox.setText("Edit selected room");
		VerticalPanel dialogVPanel = new VerticalPanel();
		HorizontalPanel hPanel = new HorizontalPanel();
		// Make a new list box, adding a few items to it.
		final ListBox listBox1 = new ListBox();
		listBox1.addItem("Available");// available room
		listBox1.addItem("Not Available");
		

		listBox1.setVisibleItemCount(1);

		text = new HTML("Please fill all of the room data!<br>");

		Label nameLbl = new Label("Room Name : ");
		final TextBox nameTxb = new TextBox();
		nameTxb.setText(room.getRoomName());
		
		Label capacityLbl = new Label("Capacity : ");
		final TextBox capacityTxb = new TextBox();
		capacityTxb.setText(room.getCapacity()+"");
		
		Label descriptionLbl = new Label("Description : ");
		final TextBox descriptionTxb = new TextBox();
		descriptionTxb.setText(room.getDescription());
		
		Label availableLbl = new Label("Available : ");
		
		Label priceLbl = new Label("Price : ");
		final TextBox priceTxb = new TextBox(); 
		priceTxb.setText(room.getPrice());
		
		Button editRoom = new Button("Edit Room");
		Button clear = new Button("Clear Fields");

		Button close = new Button("Close window");

		editRoom.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (nameTxb.getText().equals("") || capacityTxb.getText().equals("") || descriptionTxb.getText().equals("")
						|| priceTxb.getText().equals("") ) {
					errorBox("Please fill all the fields!");
				}

				else {
					int roomAvailable;
					String temp = listBox1.getSelectedItemText();
					if(temp=="Not Available"){
						roomAvailable = 0;
					}else
						roomAvailable = 1;
					
					
					roomService.editRoomFromAdmin(nameTxb.getText(), Integer.parseInt(capacityTxb.getText()), descriptionTxb.getText(), roomAvailable,
							priceTxb.getText(), new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {

							success.setText("Room edited successfully");

						}

						@Override
						public void onFailure(Throwable caught) {
							success.setText("Error in editing the room ");


						}
					});

				}

			}
		});

		clear.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				nameTxb.setText("");
				capacityTxb.setText("");
				descriptionTxb.setText("");
				priceTxb.setText("");
				
			}
		});

		dialogVPanel.setHeight("600");
		dialogVPanel.setWidth("500");
		dialogVPanel.setSpacing(10);

		dlBox.setAnimationEnabled(true);

		dialogVPanel.add(text);

		dialogVPanel.add(nameLbl);
		dialogVPanel.add(nameTxb);
		dialogVPanel.add(capacityLbl);
		dialogVPanel.add(capacityTxb);
		dialogVPanel.add(descriptionLbl);
		dialogVPanel.add(descriptionTxb);
		dialogVPanel.add(availableLbl);
		dialogVPanel.add(listBox1);
		dialogVPanel.add(priceLbl);
		dialogVPanel.add(priceTxb);
		
		
		dialogVPanel.add(success);

		hPanel.add(editRoom);
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



