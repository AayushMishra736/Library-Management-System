package com.SpringBoot.LibraryManagementSystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBoot.LibraryManagementSystem.Entity.Users;


@Service
public interface UserTableService {
	

	public Users saveUser(Users userTable);

	public List<Users> SaveAllUser(List<Users> userTable);

	public List<Users> getAllUser();

	public Users getUserById(Long id);

	public List<Users> getsUserByName(String name);

	
	public Users updateUser(Users userTable);


}
