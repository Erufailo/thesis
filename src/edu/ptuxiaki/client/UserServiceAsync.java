package edu.ptuxiaki.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	void register(String name, String surname, String email, String password, String tel, AsyncCallback<Void> callback);

	void login(String email, String password, AsyncCallback<UserData> callback);

	void changeRole(AsyncCallback<String> callback);

	void checkSessionWithServer(String sessionID, String token, AsyncCallback<UserData> callback);

	void logout(AsyncCallback<Void> callback);

	void adminRegister(String name, String surname, String email, String password, String tel, String role,
			AsyncCallback<Void> callback);

	void adminEditUser(String name, String surname, String email, String tel, String role,
			AsyncCallback<Void> callback);

	void adminDeleteUser(String email, AsyncCallback<Void> callback);

	void getAllUsers(AsyncCallback<ArrayList<UserData>> callback);

	void getSingleUser(String email,AsyncCallback<UserData> callback);

}
