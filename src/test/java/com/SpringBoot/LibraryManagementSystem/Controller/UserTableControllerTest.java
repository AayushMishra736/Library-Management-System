package com.SpringBoot.LibraryManagementSystem.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.SpringBoot.LibraryManagementSystem.Entity.Users;
import com.SpringBoot.LibraryManagementSystem.ServiceImpl.UserTableServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = UserTableController.class)
public class UserTableControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserTableServiceImpl userService;

	@Autowired
	private ObjectMapper objectMapper;
	
	private Users user;

	@BeforeEach
	public void setUpUser() {
		user = Users.builder().userName("Twain").userAge(21).userMailId("Twain@gmail.com").gender("Male").mobileNum(7654345)
				.userAddress("Mumbai").userType("Student").build();
	}
	
	
	@DisplayName("Junit test for creating new user using rest api")
	@Test
	public void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception{
        given(userService.saveUser(any(Users.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

     
        ResultActions response = mockMvc.perform(post("/addUser")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)));

        response.andDo(print()).
                andExpect(status().isCreated());
//                .andExpect(jsonPath("$.userName",
//                        is(user.getUserName())))
//                .andExpect(jsonPath("$.userMailId",
//                        is(user.getUserMailId())))
//                .andExpect(jsonPath("$.userType",
//                        is(user.getUserType())));

    }

	@DisplayName("Junit test for get all user rest api")
	@Test
	public void givenUserObjectList_whenGetAllUser_thenReturnUserList() throws Exception {
		List<Users> userList = new ArrayList<>();
		userList.add(Users.builder().userName("Marcel").userAge(23).userMailId("Marcel@gmail.com").gender("Male")
				.mobileNum(8888889).userAddress("Mumbai").userType("Student").build());
		userList.add(Users.builder().userName("George").userAge(22).userMailId("George@gmail.com").gender("Male")
				.mobileNum(777734565).userAddress("Varanasi").userType("Student").build());
		BDDMockito.given(userService.getAllUser()).willReturn(userList);
		ResultActions response = mockMvc.perform(get("/getAllUser"));
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(userList.size())));
	}
	
	@DisplayName("Junit test for get user by id rest api")
	@Test
	public void givenUserObject_whenGetbyId_thenReturnUserObject() throws Exception {
		BDDMockito.given(userService.getUserById(user.getUserId())).willReturn(user);
		ResultActions response = mockMvc.perform(get("https://localhost:8082/getUserById/1", user.getUserId()));
		response.andExpect(status().isOk()).andDo(print());
//		        .andExpect(jsonPath("$.userId", is(user.getUserId())))
//		        .andExpect(jsonPath("$.userName", is(user.getUserName())))
//				.andExpect(jsonPath("$.userAge", is(user.getUserAge())))
//				.andExpect(jsonPath("$.gender", is(user.getGender())))
//				.andExpect(jsonPath("$.userMailId", is(user.getUserMailId())))
//				.andExpect(jsonPath("$.mobileNum", is(user.getMobileNum())))
//				.andExpect(jsonPath("$.userAddress", is(user.getUserAddress())))
//				.andExpect(jsonPath("$.userType", is(user.getUserType())));
	}
	
	@DisplayName("Junit test for get user by name rest api")
	@Test
	public void givenUserObject_whenGetbyname_thenReturnUserObject() throws Exception {
		BDDMockito.given(userService.getsUserByName(user.getUserName())).willReturn(List.of(user));
		ResultActions response = mockMvc.perform(get("https://localhost:8082/getUserByName/Twain", user.getUserName()));
		response.andExpect(status().isOk()).andDo(print());
//		        .andExpect(jsonPath("$.userName", is(user.getUserName())))
//				.andExpect(jsonPath("$.userAge", is(user.getUserAge())))
//				.andExpect(jsonPath("$.gender", is(user.getGender())))
//				.andExpect(jsonPath("$.userMailId", is(user.getUserMailId())))
//				.andExpect(jsonPath("$.mobileNum", is(user.getMobileNum())))
//				.andExpect(jsonPath("$.userAddress", is(user.getUserAddress())))
//				.andExpect(jsonPath("$.userType", is(user.getUserType())));
	}
	
	
	
	@DisplayName("Junit test for update user rest api")
	@Test
	public void givenUserObject_whenUpdateUser_thenReturnUserObject() throws JsonProcessingException, Exception {
		Users savedUser = Users.builder().userId(1L).userName("Marcel").userAge(23).userMailId("Marcel@gmail.com")
				.userAddress("Mumbai").userType("Student").build();
		Users updateUser = Users.builder().userName("Marcel").userAge(23).userMailId("Marcel@gmail.com")
				.userAddress("Navi Mumbai").userType("Student").build();

		BDDMockito.given(userService.getUserById(1l)).willReturn(savedUser);
		BDDMockito.given(userService.updateUser(any(Users.class))).willAnswer(invocation -> invocation.getArgument(0));
		ResultActions response = mockMvc.perform(put("/updateUser").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updateUser)));
		response.andExpect(status().isOk()).andDo(print());
//	   .andExpect(jsonPath("$.userAddress", is(updateUser.getUserAddress())));
	}
	
	
	
	
}
