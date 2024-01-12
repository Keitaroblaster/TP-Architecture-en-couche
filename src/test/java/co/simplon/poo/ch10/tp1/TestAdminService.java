package co.simplon.poo.ch10.tp1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import co.simplon.poo.ch10.tp1.model.User;
import co.simplon.poo.ch10.tp1.repository.impl.UserRepositoryJson;
import co.simplon.poo.ch10.tp1.service.impl.AdminServiceImpl;



public class TestAdminService {
	
	private UserRepositoryJson users;
	private AdminServiceImpl adminService;
	
	@BeforeEach
    void beforeEachTest() throws IOException {
        users = new UserRepositoryJson("data/json/users.json");
        adminService = new AdminServiceImpl(users);
        users.deleteAll();
    }
	
	@Test
	void testFindAllUsers() throws Exception {
		users.create(new User("login1","password1","email1@yahoo.fr", true));
		users.create(new User("login2","password2","email2@yahoo.fr", true));
		assertEquals(2, adminService.findAllUsers().size());
	}
	
}