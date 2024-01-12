package co.simplon.poo.ch10.tp1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.simplon.poo.ch10.tp1.model.User;
import co.simplon.poo.ch10.tp1.repository.impl.UserRepositoryJson;

public class TestUserRepository {

	private UserRepositoryJson users = new UserRepositoryJson("data/json/users.json");

	@BeforeEach
	void beforeEachTest() throws IOException {
		users.deleteAll();
	}

	@Test
	void testCreateAndRetrieve() throws IOException {
		users.create(new User("Stef", "p@$$w0rd", "stef@secudev.net", true));
		users.create(new User("Mina", "p@$$w0rd", "mina@secudev.net", true));
		List<User> testList = users.retrieve();
		assertEquals(2, testList.size());
	}

	@Test
	void testUpdate() throws Exception {
		User stef = users.create(new User("Stef", "p@$$w0rd", "stef@secudev.net", true));
		assertEquals("stef@secudev.net", users.getByLogin("Stef").getEmail());
		stef.setEmail("stef@gmail.com");
		users.update(stef, stef.getId());
		assertEquals("stef@gmail.com", users.getByLogin("Stef").getEmail());
	}

	@Test
	void testDelete() throws IOException {
		User stef = users.create(new User("Stef", "p@$$w0rd", "stef@secudev.net", true));
		assertEquals(1, users.count());

		try {
			users.delete(stef.getId());
			assertEquals(0, users.count());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindByLogin() throws IOException {
		users.create(new User("Stef", "p@$$w0rd", "stef@secudev.net", true));
		users.create(new User("Mina", "p@$$w0rd", "mina@secudev.net", true));

		assertNotNull(users.getByLogin("Stef"));
		assertNotNull(users.getByLogin("Mina"));
		assertNull(users.getByLogin("Steph"));
		assertNull(users.getByLogin("mina"));
	}

	@Test
	void testFindById() throws IOException {
		String id = users.create(new User("Stef", "p@$$w0rd", "stef@secudev.net", true)).getId();
		assertEquals("Stef", users.getById(id).getLogin());
	}
}
