package com.SpringBoot.LibraryManagementSystem.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.LibraryManagementSystem.Entity.Users;
import com.SpringBoot.LibraryManagementSystem.Repository.UsersRepository;
import com.SpringBoot.LibraryManagementSystem.Service.UserTableService;

@Service
public class UserTableServiceImpl implements UserTableService {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserTableServiceImpl.class);
	
	@Autowired
	private UsersRepository  userRepository;
	
	public Users saveUser(Users userTable) {
		log.info("User service called for save book.");
		return userRepository.save(userTable);
	}
	
	public List<Users> SaveAllUser(List<Users> userTable) {
		log.info("User service called for saving all users.");
		return userRepository.saveAll(userTable);
	}
	
	public List<Users> getAllUser() {
		log.info("User service called for getting all users.");
		return userRepository.findAll();
	}
	
	public Users getUserById(Long id) {
		log.info("User service called for getting user by Id.");
		return userRepository.findByuserId(id);
	}
	
	public List<Users> getsUserByName(String name) {
		log.info("User service called for getting user by name.");
		return userRepository.findByuserName(name);
	}
	
	
	public Users updateUser(Users userTable) {
		log.info("User service called for updating user.");
		Users existingUser = userRepository.findById(userTable.getUserId()).orElse(null);
		existingUser.setUserName(userTable.getUserName());
		existingUser.setUserAge(userTable.getUserAge());
		existingUser.setGender(userTable.getGender());
		existingUser.setUserMailId(userTable.getUserMailId());
		existingUser.setMobileNum(userTable.getMobileNum());
		existingUser.setUserAddress(userTable.getUserAddress());
		existingUser.setUserType(userTable.getUserType());
		return userRepository.save(existingUser);
	}


	
}

