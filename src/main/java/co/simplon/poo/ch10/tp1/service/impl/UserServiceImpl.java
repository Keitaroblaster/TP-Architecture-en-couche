package co.simplon.poo.ch10.tp1.service.impl;

import co.simplon.poo.ch10.tp1.model.User;
import co.simplon.poo.ch10.tp1.repository.UserRepository;
import co.simplon.poo.ch10.tp1.service.UserService;

public class UserServiceImpl implements UserService {

	private final UserRepository users;

	public UserServiceImpl(UserRepository users) {
		this.users = users;
	}

	@Override
	public void changeMyPassword(String userId, String oldPass, String newPass) throws Exception {
		User targetUser = users.getById(userId);

		// Pourquoi ce n'est pas safe de faire comme çà, avec le mot de passe en clair : en raison des problèmes de sécurité
		/* ????? Comment devra t-on faire plus tard ? : les mots de passe devront être stockés en tant qu'empreintes de hachage 
			sécurisées c'est à dire de convertir le mot de passe en une empreinte unique irréversible qui ne permet pas de retransformer
			l'empreinte en donnée d'origine et de comparer avec celle stockée. Si les empreintes correspondent alors le mot de passe est 
			considéré comme correcte
		*/
		if (targetUser.getPassword().equals(oldPass)) {
			targetUser.setPassword(newPass);
			users.update(targetUser, userId);
		} else
			throw new Exception("bad old password");
	}

	@Override
	public void changeMyEmail(String userId, String newEmail) throws Exception {
		User targetUser = users.getById(userId);
		if (targetUser != null) {
			targetUser.setEmail(newEmail);
			users.update(targetUser, userId);
		}
	}

}
