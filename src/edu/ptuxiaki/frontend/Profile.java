package edu.ptuxiaki.frontend;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import edu.ptuxiaki.client.BookingData;
import edu.ptuxiaki.client.BookingService;
import edu.ptuxiaki.client.BookingServiceAsync;

public class Profile extends Composite {

	
	// instance of the class
	static private Profile _instance = null;
	FlowPanel fp = new FlowPanel();
	final BookingServiceAsync bookingService = GWT.create(BookingService.class);
	private int selection = -1;
	VerticalPanel panel = new VerticalPanel();
	private String name;
	final HTML label = new HTML();
	
	
	public Profile() {
		initPage();
		initWidget(fp);

	}
	// Singleton for creating one instance of a page
	public static Profile getInstance() {
		if (_instance == null) {
			_instance = new Profile();
		}
		return _instance;
	}

	public void initPage() {

		
		final CellTable<BookingData> table = createTable();
		
		 
		panel.setBorderWidth(1);
		panel.setWidth("500");
		
		panel.add(label);
		panel.add(table);
		
		
		
		panel.addStyleName("center");
		label.addStyleName("labelCenter");
		
		
		
		fp.add(panel);
		fp.addStyleName("mainContent");
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
		int userId= Integer.parseInt(Cookies.getCookie("userId"));
		bookingService.getBooking(userId, new AsyncCallback<ArrayList<BookingData>>() {
			
			@Override
			public void onSuccess(ArrayList<BookingData> result) {
				 //Push the data into the widget.
				table.setRowCount(result.size(), true);
				table.setRowData(0, result);
			//	setName(result.get(0).getFirstName()+" "+result.get(0).getLastName());
				label.setText("Hello "+result.get(0).getFirstName()+" "+result.get(0).getLastName());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("error");
				
			}
		});
	

		return table;
	}
	public int getSelection() {
		return selection;
	}

	public void setSelection(int i) {
		this.selection = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
