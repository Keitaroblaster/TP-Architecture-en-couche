package co.simplon.poo.ch10.tp1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.simplon.poo.ch10.tp1.model.User;
import co.simplon.poo.ch10.tp1.repository.impl.UserRepositoryJson;
import co.simplon.poo.ch10.tp1.service.UserService;
import co.simplon.poo.ch10.tp1.service.impl.UserServiceImpl;

public class TestUserService {	
	
	private UserRepositoryJson users = new UserRepositoryJson("data/json/users.json");
	private UserService userService = new UserServiceImpl(users);

	@BeforeEach
	void beforeEachTest() throws IOException {
		users.deleteAll();
	}

	@Test
	void testChangeMyPassword() throws Exception {
		
		// 10 fake users creation
		for (int i = 1; i < 10; i++) {
			users.create(new User("user" + i, "passworduser" + i, "user" + i + "@gmail.com", true));
		}
		
		// Retrieve first fake user named user1
		User user1 = users.getByLogin("user1");

		//calling service method
		userService.changeMyPassword(user1.getId(), user1.getPassword(), "newpassworduser1");
		
		//Controlling
		assertEquals("newpassworduser1", users.getByLogin("user1").getPassword());		
		
	}
	
	@Test
	void testChangeMyEmail() throws Exception {
		for (int i = 1; i < 10; i++) {
			users.create(new User("user" + i, "passworduser" + i, "user" + i + "@gmail.com", true));
		}
		User user1 = users.getByLogin("user1");
		userService.changeMyEmail(user1.getId(), "newEmailUser1");
		assertEquals("newEmailUser1", users.getByLogin("user1").getEmail());
	}
}

