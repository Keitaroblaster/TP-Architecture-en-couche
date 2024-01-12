package co.simplon.poo.ch10.tp1.service;

import java.util.List;

import co.simplon.poo.ch10.tp1.model.User;

public interface AdminService {
	
	List<User> findAllUsers();

	void resetAndSendNewPassword(String userId) throws Exception;
	
	void disableUser(String userId) throws Exception;
	
	void enableUser(String userId) throws Exception;
}
