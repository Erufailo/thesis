package edu.ptuxiaki.frontend;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.appengine.repackaged.org.joda.time.format.DateTimeFormat;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import edu.ptuxiaki.client.BookingService;
import edu.ptuxiaki.client.BookingServiceAsync;

public class Booking extends Composite {
		
		
	   private static final BookingServiceAsync bookingService = GWT.create(BookingService.class);

		// instance of the class
		static private Booking _instance = null;
		private FlowPanel fp = new FlowPanel();
		//private Grid grid = new Grid(3, 2);
		private Button checkDates = new Button("Check Dates");
		public String day1 ="";
		public String day2 ="";

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
		      DatePicker datePicker = new DatePicker();
		      final Label text = new Label();
		      
		      // Set the value in the text box when the user selects a date
		      datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
				
				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {
					Date date = event.getValue();
				    day1 = DateTimeFormat.getFormat("dd-MM-yyyy").format(date);
				      
				      text.setText(day1);
				      
				      
					
				}
			});
		      
		      
		     
		//       Set the default value
		      datePicker.setValue(new Date(), true);

		      // Create a DateBox
		      DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd-MM-yyyy");
		      final DateBox dateBox = new DateBox();
		      dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		      
		     final  Label test = new Label();
		      dateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
				
				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {
					day2= dateBox.getTextBox().getText();
					test.setText(dateBox.getTextBox().getText());
					
				}
			});
		      

		      Label selectLabel = new Label("Permanent DatePicker:");
		      Label selectLabel2 = new Label("DateBox with popup DatePicker:");
		      
		      
		      checkDates.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					bookingService.checkDates(day1, day2, new AsyncCallback<String>() {
						
						@Override
						public void onSuccess(String result) {
							test.setText("day difrence = "+ result);
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}
					});
					
				}
			});
		      HTML html = new HTML(""
		    		  
		    		  
		    	
		      
		    + " <form name='paypalForm' action='https://www.sandbox.paypal.com/cgi-bin/webscr' method='post'>"
		    + " <input type='hidden' name='cmd' value='_xclick' />"
		    + " <input type='hidden' name='business' value='jrin2113-facilitator@gmail.com' />"
		    + " <input type='hidden' name='password' value='LM7N6WSADZYKCNWP' />"
		    +  "<input type='hidden' name='custom' value='1123' />"
		    +  "<input type='hidden' name='item_name' value='Computer-Laptop' />"
		    +  "<input type='hidden' name='amount' value='30'/>"
		    +  "<input type='hidden' name='rm' value='1' />"
		    +  "<input type='hidden' name='return' value='http://localhost:8080/PaypalGS/paypalResponse.jsp' />"
		    +  "<input type='hidden' name='cancel_return' value='http://localhost:8080/PaypalGS/paypalResponseCancel.jsp' />"
		    +  "<input type='hidden' name='cert_id' value='AFcWxV21C7fd0v3bYYYRCpSSRl31AFj1duktoD.0Yb3cwn10KtP2dcUg' />"
		    +"  <input type='image' src='https://www.paypalobjects.com/en_US/i/btn/btn_buynowCC_LG.gif' border='0' name='submit' alt='PayPal - The safer, easier way to pay online!'>"
		    + "</form>"
		      );
		      
		 
		      // Add widgets to the root panel.
		      VerticalPanel vPanel = new VerticalPanel();
		      vPanel.setSpacing(10);
		      vPanel.add(selectLabel);
		      vPanel.add(text);
		      vPanel.add(datePicker);
		      vPanel.add(selectLabel2);
		      vPanel.add(dateBox);	
		      vPanel.add(test);
		      vPanel.add(checkDates);
		      vPanel.add(html);
			
			

			

			

			
			fp.add(vPanel);
		    fp.addStyleName("mainContent");
			
		}
	}

