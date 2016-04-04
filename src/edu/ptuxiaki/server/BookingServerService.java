package edu.ptuxiaki.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;



import edu.ptuxiaki.client.BookingService;

public class BookingServerService extends RemoteServiceServlet implements BookingService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String checkDates(String day1, String day2) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	try {
			Date date1 = sdf.parse(day1);
			Date date2 = sdf.parse(day2);
			
			//calculate the days
			int daysBetween = CalendarUtil.getDaysBetween(date1,date2);
			System.out.println(sdf.format(date1));
			System.out.println(sdf.format(date2));
			System.out.println(daysBetween);
			return daysBetween+"days";
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
		return null;
	}
	
	
	

}
