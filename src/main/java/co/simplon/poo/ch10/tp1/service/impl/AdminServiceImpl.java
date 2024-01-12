package co.simplon.poo.ch10.tp1.service.impl;

import java.util.List;
import co.simplon.poo.ch10.tp1.model.User;
import co.simplon.poo.ch10.tp1.repository.UserRepository;
import co.simplon.poo.ch10.tp1.service.AdminService;
import co.simplon.poo.ch10.tp1.utils.communication.FakeMailUtil;
import co.simplon.poo.ch10.tp1.utils.security.PasswordTools;

public class AdminServiceImpl implements AdminService {
	
	private final UserRepository userRepository;
	
	public AdminServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public List<User> findAllUsers() {
		return userRepository.getAllUsers();
		
	}
	
	@Override
	public void resetAndSendNewPassword(String userId) throws Exception {
		
		User user = userRepository.getById(userId);
		if(user != null) {
			String newPassword = PasswordTools.generateRandomPassword();
			user.setPassword(newPassword);
			userRepository.update(user, userId);
			FakeMailUtil.sendFakeEmail("email","passeword reset","your new password: " + newPassword);
		}
	}
	   
	@Override
	public void disableUser(String userId) throws Exception {
		User user = userRepository.getById(userId);
		if (user != null) {
            if (user.isEnable()) {
                user.setEnable(false);
                userRepository.update(user, userId);
            } else {
                throw new Exception("User is already disabled");
            }
        } else {
            throw new Exception("User not found");
        }
		
	}
	
	@Override
	public void enableUser(String userId) throws Exception {
		User user = userRepository.getById(userId);
		if(user != null) {
			if(user.isEnable()) {
				user.setEnable(true);
				userRepository.update(user, userId);
			}else {
				throw new Exception("User is already enabled");
			}
		}else {
			throw new Exception("User not found");
		}
		
	}

}
