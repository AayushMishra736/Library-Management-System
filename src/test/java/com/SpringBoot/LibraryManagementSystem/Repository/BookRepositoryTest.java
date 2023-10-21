package com.SpringBoot.LibraryManagementSystem.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.bind.annotation.GetMapping;

import com.SpringBoot.LibraryManagementSystem.Entity.Book;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository bookRepository;

	private Book book;


	@BeforeEach
	public void setup() {
		book = Book.builder().activeFlag(1).author("Jokha Alharthi").bookPrice(499).name("Celestial Bodies")
				.noOfCopies(10).publication("Paperback").build();
	}

	@DisplayName("Junit test for adding single row.")
	@Test
	public void givenBookObject_whenSave_thenReturnSavedBook() {
		book = Book.builder().activeFlag(1).author("Jokha Alharthi").bookPrice(499).name("Celestial Bodies")
				.noOfCopies(10).publication("Paperback").build();

		Book booksaved = bookRepository.save(book);
		assertThat(booksaved).isNotNull();
		assertThat(booksaved.getBookId()).isGreaterThan(0);
	}

    @DisplayName("JUnit test for getting all books.")
    @Test
    public void givenBookList_whenFindAll_thenBookList(){

    	bookRepository.save(book);
    	List<Book> allBook = bookRepository.findAll();
        assertThat(allBook.size()).isGreaterThanOrEqualTo(14);

    }

    @DisplayName("JUnit test for getting book by id.")
    @Test
    public void givenBookObject_whenFindById_thenReturnBookObject(){

        bookRepository.save(book);

        Book bookDB = bookRepository.findById(book.getBookId()).get();

        assertThat(bookDB).isNotNull();
    }

    
    @DisplayName("JUnit test for getting book by Name.")
    @Test
    public void givenBookObject_whenFindByName_thenReturnBookObject(){
    bookRepository.save(book);	
    	
    List<Book> bookdb = bookRepository.findByName(book.getName());
    assertThat(bookdb).isNotNull();
    }
    
    @DisplayName("JUnit test for getting book by Author.")
    @Test
    public void givenBookObject_whenFindByAuthor_thenReturnBookObject(){
    bookRepository.save(book);	
    	
    List<Book> bookdb = bookRepository.findByauthor(book.getAuthor());
    assertThat(bookdb).isNotNull();
    
    }
    @DisplayName("JUnit test for getting book by Publication.")
    @Test
    public void givenBookObject_whenFindByPubliction_thenReturnBookObject(){
    bookRepository.save(book);	
    	
    List<Book> bookdb = bookRepository.findBypublication(book.getPublication());
    assertThat(bookdb).isNotNull();
    
    }

    @DisplayName("JUnit test for update book operation")
    @Test
    public void givenBookObject_whenUpdateBookData_thenReturnUpdatedBookData(){

    	bookRepository.save(book);
    	
        Book savedBook = bookRepository.findById(book.getBookId()).get();
        savedBook.setActiveFlag(0);
        savedBook.setBookPrice(900);
        
        Book updatedBook =  bookRepository.save(savedBook);
        
        assertThat(updatedBook.getActiveFlag()).isEqualTo(0);
        assertThat(updatedBook.getBookPrice()).isEqualTo(900);
    }

    @DisplayName("JUnit test for delete(Update Active Flag To 0) book.")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee(){

        bookRepository.save(book);

        Book checkActiveFlag = bookRepository.getById(book.getBookId());
        checkActiveFlag.setActiveFlag(0);
        Book updatedBookData = bookRepository.save(checkActiveFlag);
        
         
        assertThat(updatedBookData.getActiveFlag()).isEqualTo(0);
    }
}
	
	
	
	