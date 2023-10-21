package com.SpringBoot.LibraryManagementSystem.Repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.SpringBoot.LibraryManagementSystem.Entity.Users;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsersRepositoryTest {
	
	@Autowired
	private UsersRepository userRepository;
	
	private Users user;
	

	@BeforeEach
	public void setUpUser() {
		user	=	Users.builder().userName("Aayush").userAddress("79 Phase- II").gender("Male").mobileNum(908765789)
				.userAge(24).userType("Student").userMailId("def@gmail.com").build();
	}
	
	@DisplayName("Junit test for save user.")
	@Test
	public void givenUserObject_whenSave_thenReturnUser() {
    Users users = Users.builder().userName("Aayush").userAddress("79 Phase- II").gender("Male").mobileNum(908765789)
					.userAge(24).userType("Student").userMailId("def@gmail.com").build();
		  userRepository.save(users);
		  Users found = userRepository.findById(users.getUserId()).get();
	      assertEquals(users.getUserId(), found.getUserId());	
	}
	
	@DisplayName("Junit test for saving multiple user.")
	@Test
	public void givenUserObject_whenSaveall_thenReturnUser() {
	userRepository.save(user);
	List<Users> allBook = userRepository.findAll();
    assertThat(allBook.size()).isGreaterThanOrEqualTo(9);	
	}
	
	@DisplayName("Junit Test for get All Employee.")
	@Test
	public void givenUserListObject_whenFindAll_thenReturnUserList() {
		
		userRepository.save(user);
		
		List<Users> userList = userRepository.findAll();
		assertThat(userList).isNotNull();
		assertThat(userList.size()).isEqualTo(9);
	}
	
	@DisplayName("Junit test for getting user by user id.")
	@Test
	public void givenUserObject_whenFindById_thenReturnUser() {
		userRepository.save(user);
		Users savedUser = userRepository.findById(user.getUserId()).get();
		assertThat(savedUser).isNotNull();
	}
	
	@DisplayName("Junit test for getting user by name.")
	@Test
	public void givenUserObject_whenFindByName_thenReturnUser() {
		userRepository.save(user);
		List<Users> savedUser = userRepository.findByuserName(user.getUserName());
		assertThat(savedUser).isNotNull();
	}
	
	@DisplayName("Junit test for update user")
	@Test
	public void givenUserObject_whenUpdate_thenReturnUser() {
		userRepository.save(user);
		Users savedUser = userRepository.findById(user.getUserId()).get();
						savedUser.setUserMailId("rahul@gmail.com");
						savedUser.setMobileNum(99999911);
		Users updateUser = userRepository.save(savedUser);
		assertThat(updateUser.getUserMailId()).isEqualTo("rahul@gmail.com");
		assertThat(updateUser.getMobileNum()).isEqualTo(99999911);
	}
	
	


}
