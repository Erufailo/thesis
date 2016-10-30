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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import edu.ptuxiaki.client.BookingData;
import edu.ptuxiaki.client.BookingService;
import edu.ptuxiaki.client.BookingServiceAsync;
import edu.ptuxiaki.client.RoomService;
import edu.ptuxiaki.client.RoomServiceAsync;



public class AdminPanel extends Composite {

	
	// instance of the class
	final RoomServiceAsync roomService = GWT.create(RoomService.class);
	final BookingServiceAsync bookingService = GWT.create(BookingService.class);
	static private AdminPanel _instance = null;
	FlowPanel fp = new FlowPanel();
	
	HorizontalPanel navPanel = new HorizontalPanel();
	
	VerticalPanel panel = new VerticalPanel();
	HorizontalPanel bottomPanel = new HorizontalPanel();
	//ArrayList<UserData> USERS = new ArrayList<>();
	
	private int selection = -1;

	public AdminPanel() {
		initPage();
		initWidget(fp);

	}
	// Singleton for creating one instance of a page
	public static AdminPanel getInstance() {
		if (_instance == null) {
			_instance = new AdminPanel();
		}
		return _instance;
	}

	public void initPage() {
		final CellTable<BookingData> table = createTable();
		
		HTML label = new HTML("<h1>Checked-in Customers</h1>");
		Button updateButton = new Button("Update Table");
		
	
		
		updateButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				bookingService.getBookings(true, new AsyncCallback<ArrayList<BookingData>>() {

					@Override
					public void onSuccess(ArrayList<BookingData> result) {
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
		

		
		bottomPanel.add(updateButton);

		panel.setBorderWidth(1);
		panel.setWidth("500");
		panel.add(label);
		panel.add(table);
		panel.add(bottomPanel);
		
		panel.addStyleName("center");
		fp.add(panel);
		// fp.add(label);
		
		fp.addStyleName("adminMainContent");
	}
	
	
	public CellTable<BookingData> createTable() {

		final CellTable<BookingData> table = new CellTable<BookingData>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a text column to show the name.
		

		TextColumn<BookingData> bookingIdColumn = new TextColumn<BookingData>() {
			@Override
			public String getValue(BookingData object) {
				return object.getBookingId()+"";
			}
		};
		table.addColumn(bookingIdColumn, "Booking Number");

		
		TextColumn<BookingData> nameColumn = new TextColumn<BookingData>() {
			@Override
			public String getValue(BookingData object) {
				return object.getRoomName();
			}
		};
		table.addColumn(nameColumn, "Room Name");

		TextColumn<BookingData> startDateColumn = new TextColumn<BookingData>() {
			@Override
			public String getValue(BookingData object) {
				return object.getStartDate();
			}
		};
		table.addColumn(startDateColumn, "Check-in Date");
		
		TextColumn<BookingData> endDateColumn = new TextColumn<BookingData>() {
			@Override
			public String getValue(BookingData object) {
				return object.getEndDate();
			}
		};
		table.addColumn(endDateColumn, "Check-out Date");

		TextColumn<BookingData> firstNameColumn = new TextColumn<BookingData>() {
			@Override
			public String getValue(BookingData object) {
				return object.getFirstName();
			}
		};
		table.addColumn(firstNameColumn, "First Name");
		
		TextColumn<BookingData> lastNameColumn = new TextColumn<BookingData>() {
			@Override
			public String getValue(BookingData object) {
				return object.getLastName();
			}
		};
		table.addColumn(lastNameColumn, "Last Name");
		
		TextColumn<BookingData> bookingStatusColumn = new TextColumn<BookingData>() {
			@Override
			public String getValue(BookingData object) {
				return object.getBookingStatus()+"";
			}
		};
		table.addColumn(bookingStatusColumn, "Booking Status");
						
		TextColumn<BookingData> priceColumn = new TextColumn<BookingData>() {
			@Override
			public String getValue(BookingData object) {
				return object.getPrice();
			}
		};
		table.addColumn(priceColumn, "Price");

		// Add a selection model to handle user selection.
		final SingleSelectionModel<BookingData> selectionModel = new SingleSelectionModel<BookingData>();
		table.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				BookingData selected = selectionModel.getSelectedObject();
				if (selected != null) {
					GWT.log("You selected: " + selected.getRoomName());
					setSelection(selected.getBookingId());
				}
			}
		});
		bookingService.getBookings(true, new AsyncCallback<ArrayList<BookingData>>() {
			
			@Override
			public void onSuccess(ArrayList<BookingData> result) {
				 //Push the data into the widget.
				table.setRowCount(result.size(), true);
				table.setRowData(0, result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("error");
				
			}
		});
	

		return table;
	}
	
	public void messageBox(String messageText) {
		final DialogBox dlBox = new DialogBox();
		// HTML text;
		dlBox.setTitle("Error");
		dlBox.setText("Error");

		HTML text = new HTML(messageText + "<br>");

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
	
	public int getSelection() {
		return selection;
	}

	public void setSelection(int i) {
		this.selection = i;
	}
	
}


