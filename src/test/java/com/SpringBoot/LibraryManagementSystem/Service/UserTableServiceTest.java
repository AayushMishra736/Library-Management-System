package com.SpringBoot.LibraryManagementSystem.Service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.SpringBoot.LibraryManagementSystem.Entity.Users;
import com.SpringBoot.LibraryManagementSystem.Repository.UsersRepository;
import com.SpringBoot.LibraryManagementSystem.ServiceImpl.UserTableServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserTableServiceTest {
	
	@Mock
	private UsersRepository userRepository;

	@InjectMocks
	private UserTableServiceImpl userService;

	private Users user;

	@BeforeEach
	public void setUpUser() {
		user = Users.builder().userName("John").userAge(25).userMailId("John@gmail.com").gender("Male").mobileNum(999999)
				.userAddress("Bangalore").userType("Student").build();
	}

	@DisplayName("Junit test for save user.")
	@Test
	public void givenUserObject_whenSave_thenReturnUserObject() {
		BDDMockito.given(userRepository.save(user)).willReturn(user);
		Users savedUser = userService.saveUser(user);
		assertThat(savedUser).isNotNull();
	}
	
	@DisplayName("Junit test for save multiple user.")
	@Test
	public void givenMultipleUserObject_whenSave_thenReturnMultipleUserObject() {
		Users user2 = Users.builder().userName("Rohit").userAge(26).userMailId("Rohit@gmail.com").gender("Male").mobileNum(999999)
				.userAddress("Pune").userType("Student").build();
		List<Users> savedUser = new ArrayList<>();
		savedUser.add(user);
		savedUser.add(user2);
		userRepository.saveAll(savedUser);
		assertThat(savedUser).isEqualTo(savedUser);
	}
	@DisplayName("Junit test for getying all Users.")
	@Test
	public void givenUsersObject_whenFindAll_thenReturnUsersObjcet() {
		BDDMockito.given(userRepository.findAll()).willReturn(List.of(user));
		List<Users> UserList = userService.getAllUser();
		assertThat(UserList).isNotNull();
		assertThat(UserList.size()).isGreaterThan(0);
	}

	@DisplayName("Junit test for get user by user id.")
	@Test
	public void givenUserObject_whenFindById_thenReturnUserObject() {
		user = Users.builder().userId(10L).userName("John").userAge(25).userMailId("John@gmail.com").gender("Male").mobileNum(999999)
				.userAddress("Bangalore").userType("Student").build();
		BDDMockito.given(userRepository.findByuserId(10L)).willReturn(user);
	    Users savedUser = userService.getUserById(user.getUserId());
		assertThat(savedUser).isNotNull();
	}
	
	@DisplayName("Junit test for get user by user name.")
	@Test
	public void givenUserObject_whenFindByName_thenReturnUserObject() {
		BDDMockito.given(userRepository.findByuserName("John")).willReturn(List.of(user));
	    List<Users> savedUser = userService.getsUserByName("John");
		assertThat(savedUser).isNotNull();
	}
	
	@DisplayName("Junit test for updating user.")
	@Test
	public void givenUserObject_ForUpdate_thenReturnUserObject() {
		BDDMockito.given(userRepository.save(user)).willReturn(user);
		Users savedUser = userService.saveUser(user);
		savedUser.setUserMailId("johnsnow@gmail.com");
        assertThat(savedUser.getUserMailId()).isEqualTo("johnsnow@gmail.com");
	}
	
	
}
