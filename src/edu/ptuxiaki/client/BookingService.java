package edu.ptuxiaki.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("booking")
public interface BookingService extends RemoteService {
	public String checkDates(String day1, String day2);
}
