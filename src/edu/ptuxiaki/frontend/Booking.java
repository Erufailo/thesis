package edu.ptuxiaki.frontend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.appengine.repackaged.org.joda.time.format.DateTimeFormat;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import edu.ptuxiaki.client.BookingService;
import edu.ptuxiaki.client.BookingServiceAsync;
import edu.ptuxiaki.client.RoomData;
import edu.ptuxiaki.client.RoomService;
import edu.ptuxiaki.client.RoomServiceAsync;

public class Booking extends Composite {
		
		
	   private static final BookingServiceAsync bookingService = GWT.create(BookingService.class);
	   private static final RoomServiceAsync roomService = GWT.create(RoomService.class);

		// instance of the class
		static private Booking _instance = null;
		private FlowPanel fp = new FlowPanel();
		//private Grid grid = new Grid(3, 2);
		private HorizontalPanel horizontalPanel = new HorizontalPanel();
		private VerticalPanel vPanel = new VerticalPanel();
		private VerticalPanel vCheckIn = new VerticalPanel();
		private VerticalPanel vCheckout = new VerticalPanel();
		private VerticalPanel vRoomButton = new VerticalPanel();
		private String selection = null;
		private String day1 =null;
		private String day2 =null;
		private String price="";
		private int roomId=0;



	

		public Booking() {
			initPage();
			initWidget(fp);

		}

		// Singleton for creating one instance of a page
		public static Booking getInstance() {
			if (_instance == null) {
				_instance = new Booking();
			}
			return _instance;
		}

		public void initPage() {
			// Create a basic date picker
			final CellTable<RoomData> table = createTable();
			final Button finalize = new Button("Finalize Selection");
			
			DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd-MM-yyyy");
		      final DateBox dateBox = new DateBox();
		      dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		      
		      final  Label checkIn = new Label("Check-in Date");
		      dateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
				
				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {
					
					day1=(dateBox.getTextBox().getText());
					
				}
			});
		      final DateBox dateBox2 = new DateBox();
		      dateBox2.setFormat(new DateBox.DefaultFormat(dateFormat));
		      
		      final  Label checkOut = new Label("Check-out Date");
		      dateBox2.addValueChangeHandler(new ValueChangeHandler<Date>() {
				
				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {
					
					day2=(dateBox2.getTextBox().getText());
					
				}
			});
		      final Label roomCheckLabel= new Label("Check Available Rooms");
		      Button checkRooms = new Button("Search");
		      
		      
		      checkRooms.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					if(day1 ==null || day2==null){//check if dateboxes are empty
						errorBox("Please select Days on both fields");
					}else {
						bookingService.checkDates(day1, day2, new AsyncCallback<String>() {
							
							@Override//check if the checkout date is smaller than checkin
							public void onSuccess(String result) {
								int diff=Integer.parseInt(result);
								if(diff<=0){
									errorBox("Please select valid dates");
								}else{
									bookingService.isOldDates(day1, day2, new AsyncCallback<Boolean>() {
										
										@Override//check if any of the dates are past the today
										public void onSuccess(Boolean result) {
											if (result){
												errorBox("Please select valid dates, you entered a date older than today");
											}else{
												bookingService.getAvailableRooms(dateBox.getTextBox().getText(), dateBox2.getTextBox().getText(), new AsyncCallback<ArrayList<RoomData>>() {
													
													@Override//show the available rooms
													public void onSuccess(ArrayList<RoomData> result) {
														
														
														table.setRowData(result);
														vPanel.add(table);
														vPanel.add(finalize);
														
														
														
													}
													
													@Override
													public void onFailure(Throwable caught) {
														// TODO Auto-generated method stub
														
													}
												});
												
											}
											
										}
										
										@Override
										public void onFailure(Throwable caught) {
											// TODO Auto-generated method stub
											
										}
									});
								
								}
											
										}
										
										@Override
										public void onFailure(Throwable caught) {
											// TODO Auto-generated method stub
											
										}
									});
								}
								
								
					
				
					
				}
			});
		      
		    
		      finalize.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					bookingService.checkDates(day1, day2, new AsyncCallback<String>() {
						
						@Override
						public void onSuccess(String result) {
							//calculate the days
							int daysBetween = Integer.parseInt(result);
							int totalPrice = (Integer.parseInt(getPrice())*daysBetween);
							
							 HTML paypal = new HTML(""
						    		  
			    + " <form name='paypalForm' action='https://www.sandbox.paypal.com/cgi-bin/webscr' method='post'>"
			    + " <input type='hidden' name='cmd' value='_xclick' />"
			    + " <input type='hidden' name='business' value='jrin2113-facilitator@gmail.com' />"
			    + " <input type='hidden' name='password' value='LM7N6WSADZYKCNWP' />"
			    +  "<input type='hidden' name='custom' value='1123' />"
			    +  "<input type='hidden' name='item_name' value='Hotel Room "+getSelection() +"' />"
			    +  "<input type='hidden' name='amount' value='"+totalPrice+"'/>"
			    +  "<input type='hidden' name='rm' value='1' />"
			    +  "<input type='hidden' name='return' value='http://127.0.0.1:8888/Ptuxiaki_Hotel2997.html#success' />"
			    +  "<input type='hidden' name='cancel_return' value='http://127.0.0.1:8888/Ptuxiaki_Hotel2997.html#booking' />"
			    +  "<input type='hidden' name='cert_id' value='AFcWxV21C7fd0v3bYYYRCpSSRl31AFj1duktoD.0Yb3cwn10KtP2dcUg' />"
			    +"  <input type='image' src='https://www.paypalobjects.com/en_US/i/btn/btn_buynowCC_LG.gif' border='0' name='submit' alt='PayPal - The safer, easier way to pay online!'>"
			    + "</form>"
			      );	
						//if (paypal.isAttached()){
						//	vPanel.remove(paypal);
						//}
							 GWT.log("Booking prin paypla");
						vPanel.add(paypal);	
						
								int userId= Integer.parseInt(Cookies.getCookie("userId"));
								GWT.log(userId+" "+getRoomId()+" "+day1+" "+day2);
								bookingService.addBooking(day1, day2, userId, getRoomId(), new AsyncCallback<Void>() {
									
									@Override
									public void onSuccess(Void result) {
										finalize.setEnabled(false);
										
									}
									
									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										
									}
								});
								
						
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							GWT.log("failed Booking");
							
						}
					});
						
					
				}
			});
		      
		     
		     
		      
		 
		      // Add widgets to the root panel.
		      
		      
		      
		      vCheckIn.add(checkIn);
		      vCheckIn.add(dateBox);
		      horizontalPanel.add(vCheckIn);
		      vCheckout.add(checkOut);
		      vCheckout.add(dateBox2);
		      horizontalPanel.add(vCheckout);
		      vRoomButton.add(roomCheckLabel);
		      vRoomButton.add(checkRooms);

		      horizontalPanel.add(vRoomButton);
		      
		      
		      vPanel.setSpacing(10);
		      vPanel.add(horizontalPanel);
		      
		      //vPanel.add(paypal);
			
			

			

			

			
			fp.add(vPanel);
		    fp.addStyleName("mainContent");
			
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
					final RoomData selected = selectionModel.getSelectedObject();
					if (selected != null) {
						GWT.log("You selected: " + selected.getRoomName());
						setSelection(selected.getRoomName());
						setPrice(selected.getPrice());
						setRoomId(selected.getId());
						
						
					
					}
				}
			});
			
			return table;
		
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
		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}
		
		public int getRoomId() {
			return roomId;
		}

		public void setRoomId(int roomId) {
			this.roomId = roomId;
		}
}

