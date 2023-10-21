package com.SpringBoot.LibraryManagementSystem.Controller;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.http.MediaType;

import com.SpringBoot.LibraryManagementSystem.Entity.Book;
import com.SpringBoot.LibraryManagementSystem.Entity.Users;
import com.SpringBoot.LibraryManagementSystem.Service.BookService;
import com.SpringBoot.LibraryManagementSystem.ServiceImpl.UserTableServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = BookController.class)
public class BookControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Autowired
	private ObjectMapper objectMapper;
	
	private Book book;
	
	
	@DisplayName("Junit test for creating new book using rest api")
    @Test
    public void givenBookObject_whenInsertbook_thenReturnSavedBook() throws Exception{
        Book book = Book.builder()
                .activeFlag(1).author("Erica Jong").bookPrice(700).name("Fear of Flying")
                .noOfCopies(10)
                .build();
        BDDMockito.given(bookService.saveBook(1L,book)).willReturn(book);

        ResultActions response = mockMvc.perform(post("/books/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(book)));
        response.andExpect(status().isCreated()).
        andDo(print());

    }

	@DisplayName("Junit test for get all books using rest api")
	@Test
	public void givenBookObjectList_whenGetAllUser_thenReturnBookList() throws Exception {
		List<Book> bookList = new ArrayList<>();
		bookList.add(Book.builder().bookId(1L)
                .activeFlag(1).author("Erica Jong").bookPrice(700).name("Fear of Flying")
                .noOfCopies(10)
                .build());
		bookList.add(Book.builder().bookId(2L)
                .activeFlag(1).author("Paul Kalanithi").bookPrice(700).name("When Breath Becomes Air")
                .noOfCopies(10)
                .build());
		BDDMockito.given(bookService.getAllBook()).willReturn(bookList);
		ResultActions response = mockMvc.perform(get("/getAllBook"));
		response.andExpect(status().isOk());
	}
	
	@DisplayName("Junit test for get book by book name using rest api")
	@Test
	public void givenBookObject_whenGetbyName_thenReturnBookObject() throws Exception {
		 Book book = Book.builder()
	                .activeFlag(1).author("Erica Jong").bookPrice(700).name("Fear of Flying")
	                .noOfCopies(10)
	                .build();
		BDDMockito.given(bookService.findBookBybook_Name("Fear of Flying")).willReturn(List.of(book));
		ResultActions response = mockMvc.perform(get("/BookByName/Fear of Flying", book.getName()));
		response.andExpect(status().isOk()).andDo(print());
		
	}
	@DisplayName("Junit test for get book by author name using rest api")
	@Test
	public void givenBookObject_whenGetbyAuthor_thenReturnBookObject() throws Exception {
		 Book book = Book.builder()
	                .activeFlag(1).author("Erica Jong").bookPrice(700).name("Fear of Flying")
	                .noOfCopies(10)
	                .build();
		BDDMockito.given(bookService.getsBookByAuthorName("Erica Jong")).willReturn(List.of(book));
		ResultActions response = mockMvc.perform(get("/BookByAuthorName/Erica Jong", book.getAuthor()));
		response.andExpect(status().isOk()).andDo(print());
		
	}

	@DisplayName("Junit test for get book by publication name using rest api")
	@Test
	public void givenBookObject_whenGetbyPublication_thenReturnBookObject() throws Exception {
		 Book book = Book.builder()
	                .activeFlag(1).author("Erica Jong").bookPrice(700).name("Fear of Flying").publication("Penguin")
	                .noOfCopies(10)
	                .build();
		BDDMockito.given(bookService.getsBookPublication("Penguin")).willReturn(List.of(book));
		ResultActions response = mockMvc.perform(get("/BookByPublication/Penguin", book.getAuthor()));
		response.andExpect(status().isOk()).andDo(print());	
	}
	@DisplayName("Junit test for updating book using rest api")
	@Test
	public void givenBookObject_ToUpdateBookData_thenReturnBookObject() throws Exception {
		 Book savedbook = Book.builder().bookId(1L)
	                .activeFlag(1).author("Erica Jong").bookPrice(700).name("Fear of Flying").publication("Penguin")
	                .noOfCopies(10)
	                .build();
		 Book updatedbook = Book.builder().bookId(1L)
	                .activeFlag(1).author("Erica Jong").bookPrice(700).name("Fear of Flying").publication("Pearson")
	                .noOfCopies(10)
	                .build();
			BDDMockito.given(bookService.getsBookById(1l)).willReturn(savedbook);
			BDDMockito.given(bookService.updateBook(updatedbook)).willReturn(updatedbook);
		ResultActions response = mockMvc.perform(put("/updateBook", 1L));
		response.andExpect(status().isOk()).andDo(print());	
	}
	
	@DisplayName("Junit test for deleteing book using rest api")
	@Test
	public void givenBookObject_ToDeleteBookData_thenReturnBookObject() throws Exception {
		 Book book = Book.builder().bookId(1L)
	                .activeFlag(1).author("Erica Jong").bookPrice(700).name("Fear of Flying").publication("Penguin")
	                .noOfCopies(10)
	                .build();
		BDDMockito.given(bookService.deleteBook(book.getName(),1L)).willReturn(book);
		ResultActions response = mockMvc.perform(delete("/deleteBookByName/Fear of Flying/1", book.getBookId()));
		response.andExpect(status().isOk()).andDo(print());	
	}
	
}
