package co.simplon.poo.ch10.tp1.repository.impl;


import java.io.File;
import java.io.IOException;
import java.util.List;
import co.simplon.poo.ch10.tp1.model.User;
import co.simplon.poo.ch10.tp1.repository.UserRepository;
import co.simplon.poo.ch10.tp1.utils.persistence.GenericRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserRepositoryJson extends GenericRepository<User> implements UserRepository {

	public UserRepositoryJson(String jsonFilePath) {
		super(jsonFilePath);
	}

	public User getByLogin(String login) {
		
		/*
		 * A l'ancienne
		 *
			User result = null;
			for (User u : this.data) {
				if (u.getLogin().equals(login))
					result = u;
			}
		*/		
		
		//Mieux avec des stream !!
		return this.retrieve().stream().filter(p->p.getLogin().equals(login)).findFirst().orElse(null);
	}
	
	public List<User> getAllUsers() {
		return retrieve();
	}

	
}
