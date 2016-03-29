package edu.ptuxiaki.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BookingServiceAsync {

	void checkDates(String day1, String day2, AsyncCallback<String> callback);

}
