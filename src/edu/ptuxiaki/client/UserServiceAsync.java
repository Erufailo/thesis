package edu.ptuxiaki.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	void register(String name, String surname, String email, String password, String tel, AsyncCallback<Void> callback);

	void login(String email, String password, AsyncCallback<String> callback);

}
