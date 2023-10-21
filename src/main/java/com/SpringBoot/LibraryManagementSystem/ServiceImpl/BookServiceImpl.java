package com.SpringBoot.LibraryManagementSystem.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.LibraryManagementSystem.Entity.Book;
import com.SpringBoot.LibraryManagementSystem.Entity.Users;
import com.SpringBoot.LibraryManagementSystem.Repository.BookRepository;
import com.SpringBoot.LibraryManagementSystem.Repository.UsersRepository;
import com.SpringBoot.LibraryManagementSystem.Service.BookService;
import com.fasterxml.jackson.annotation.JsonFormat;

@Service
public class BookServiceImpl implements BookService{

	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BookRepository bookrepository;

	@Autowired
	private UsersRepository userrepository;

	public Book saveBook(Long libId, Book book) {
		log.info("Book service called for save book.");
		Users user = userrepository.findById(libId).get();
		book.setCreatedBy(user);
		book.setModifiedBy(user);
		return bookrepository.save(book);
	}

	public List<Book> getAllBook() {
		log.info("Book service called for getting all book.");
		return bookrepository.findAll();
	}

	public Book getsBookById(Long id) {
		log.info("Book service called for finding book by id.");
		return bookrepository.findById(id).orElse(null);
	}

	public List<Book> findBookBybook_Name(String name) {
		log.info("Book service called for finding book by name.");
		List<Book> books = new ArrayList<>();
		return bookrepository.findByName(name);

	}

	public List<Book> getsBookByAuthorName(String author) {
		log.info("Book service called for getting all book's by author name.");
		List<Book> books = new ArrayList<>();
		return bookrepository.findByauthor(author);
	}

	public List<Book> getsBookPublication(String name) {
		log.info("Book service called for getting all book's by publication name.");
		return bookrepository.findBypublication(name);
	}

	public Book deleteBook(String name, Long id) {
		log.info("Book service called for deleting all book's.");
		List<Book> existingBook = bookrepository.findByName(name);
		Users user = userrepository.findById(id).get();
		existingBook.get(0).setActiveFlag(0);
		existingBook.get(0).setNoOfCopies(0);
		existingBook.get(0).setModifiedBy(user);
		return bookrepository.save(existingBook.get(0));
	}

	public Book updateBook(Book book) {
		log.info("Book service for updating all book's.");
		Book existingBook = bookrepository.findById(book.getBookId()).orElse(null);
		existingBook.setName(book.getName());
		existingBook.setAuthor(book.getAuthor());
		existingBook.setPublication(book.getPublication());
		existingBook.setBookPrice(book.getBookPrice());
		existingBook.setNoOfCopies(book.getNoOfCopies());
		existingBook.setCreatedBy(book.getCreatedBy());
		existingBook.setCreatedOn(book.getCreatedOn());
		existingBook.setModifiedBy(book.getModifiedBy());
		existingBook.setModifiedOn(book.getModifiedOn());
		existingBook.setActiveFlag(book.getActiveFlag());
		return bookrepository.save(existingBook);
	}

}
