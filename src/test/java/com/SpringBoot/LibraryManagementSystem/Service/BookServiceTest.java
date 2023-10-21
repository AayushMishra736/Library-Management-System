package com.SpringBoot.LibraryManagementSystem.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import com.SpringBoot.LibraryManagementSystem.Entity.Book;
import com.SpringBoot.LibraryManagementSystem.Entity.Users;
import com.SpringBoot.LibraryManagementSystem.Repository.BookRepository;
import com.SpringBoot.LibraryManagementSystem.Repository.UsersRepository;
import com.SpringBoot.LibraryManagementSystem.ServiceImpl.BookServiceImpl;



@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	

    @Mock
    private BookRepository  bookRepository;
    
    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;

    
    @DisplayName("Junit test for save book service.")
	@Test
	public void givenUserObject_whenSave_thenReturnUserObject() {
    Book book = Book.builder().bookId(10L)
                .activeFlag(1).name("A Revenue Stamp")
                .author("Amrita Pritam")
                .bookPrice(670).createdOn(new Date())
                .build();
		BDDMockito.given(bookRepository.save(book)).willReturn(book);
		Book savedBook = bookService.saveBook(1L,book);
		assertThat(savedBook).isNotNull();
	}
	
	@DisplayName("Junit test for get all books")
	@Test
	public void givenBookObject_whenFindAll_thenReturnBookObjcet() {
		Book book =Book.builder().bookId(10L)
                .activeFlag(1).name("A Revenue Stamp")
                .author("Amrita Pritam")
                .bookPrice(670).createdOn(new Date())
                .build();
		BDDMockito.given(bookRepository.findAll()).willReturn(List.of(book));
		List<Book> bookList = bookService.getAllBook();
		assertThat(bookList).isNotNull();
		assertThat(bookList.size()).isGreaterThan(0);
	}

	@DisplayName("Junit test for get book by book id")
	@Test
	public void givenBookObject_whenFindById_thenReturnBookObject() {
		Book book =Book.builder().bookId(10L)
                .activeFlag(1).name("A Revenue Stamp")
                .author("Amrita Pritam")
                .bookPrice(670).createdOn(new Date())
                .build();
		BDDMockito.given(bookRepository.findById(book.getBookId())).willReturn(Optional.of(book));
		Book savedBook = bookService.getsBookById(book.getBookId());
		assertThat(savedBook).isNotNull();
	}
	
	@DisplayName("Junit test for get book by book name.")
	@Test
	public void givenBookObject_whenFindByName_thenReturnBookObject() {
		Book book =Book.builder().bookId(10L)
                .activeFlag(1).name("A Revenue Stamp")
                .author("Amrita Pritam")
                .bookPrice(670).createdOn(new Date())
                .build();
		BDDMockito.given(bookRepository.findByName(book.getName())).willReturn(List.of(book));
		List<Book> savedBook = bookService.findBookBybook_Name(book.getName());
		assertThat(savedBook).isNotNull();
	}
	@DisplayName("Junit test for get book by publication.")
	@Test
	public void givenBookObject_whenFindByPublication_thenReturnBookObject() {
		Book book =Book.builder().bookId(10L)
                .activeFlag(1).name("A Revenue Stamp")
                .author("Amrita Pritam").publication("Pearson Publication")
                .bookPrice(670).createdOn(new Date())
                .build();
		BDDMockito.given(bookRepository.findBypublication("Pearson Publication")).willReturn(List.of(book));
		List<Book> savedBook = bookService.getsBookPublication(book.getPublication());
		assertThat(savedBook).isNotNull();
	}
	
	@DisplayName("Junit test for get book by publication.")
	@Test
	public void givenBookObject_whenFindByAuthor_thenReturnBookObject() {
		Book book =Book.builder().bookId(10L)
                .activeFlag(1).name("A Revenue Stamp")
                .author("Amrita Pritam").publication("Pearson Publication")
                .bookPrice(670).createdOn(new Date())
                .build();
		BDDMockito.given(bookRepository.findByauthor("Amrita Pritam")).willReturn(List.of(book));
		List<Book> savedBook = bookService.getsBookByAuthorName(book.getAuthor());
		assertThat(savedBook).isNotNull();
	}
	
	@DisplayName("Junit test for delete book")
	@Test
	public void givenBookObject_whenDeleteById_thenReturnNothing() {
		Book book =Book.builder().bookId(10L)
                .activeFlag(1).name("A Revenue Stamp")
                .author("Amrita Pritam").publication("Pearson Publication")
                .bookPrice(670).createdOn(new Date())
                .build();
		BDDMockito.willDoNothing().given(bookRepository).deleteById(book.getBookId());
		bookService.deleteBook("Arun", 1L);
		verify(bookRepository, times(1)).deleteById(book.getBookId());
	}
	
	
}
