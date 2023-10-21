package com.SpringBoot.LibraryManagementSystem.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.LibraryManagementSystem.Entity.Book;
import com.SpringBoot.LibraryManagementSystem.Entity.Users;
import com.SpringBoot.LibraryManagementSystem.Service.UserTableService;



@RestController
public class UserTableController {
	
	private static final Logger log = LoggerFactory.getLogger(UserTableController.class);
	
	
	@Autowired
	private UserTableService service;

	@PostMapping("/addUser")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String addUser(@RequestBody Users user) {
		log.info("Adding user through json in user controller.");
		 service.saveUser(user);
		 return "User Added Successfully!!";
	}

	@PostMapping("/addMultipleUser")
	public String addMultipleUser(@RequestBody List<Users> user) {
		log.info("Adding multiple user through json in user controller.");
		 service.SaveAllUser(user);
		 return "All Users Added Successfully!!";
	}

	@GetMapping("/getAllUser")
	public List<Users> findAllUser() {
	log.info("Fetching user in json through user controller.");
		return service.getAllUser();
	}

	@GetMapping("/getUserById/{id}")
	public Users findUserById(@PathVariable Long id) {
	log.info("Fetching user in json through user controller by id.");	
		return service.getUserById(id);
	}

	@GetMapping("/getUserByName/{name}")
	public List<Users> findUserByName(@PathVariable String name) {
	log.info("Fetching user in json through user controller by name.");	
		return service.getsUserByName(name);
	}
	@PutMapping("/updateUser")
	public String  updateUser(@RequestBody Users user) {
	log.info("Updating user in json through user controller.");	
		 service.updateUser(user);
		 return "User Updated Successfully!!";
	}
		
}
