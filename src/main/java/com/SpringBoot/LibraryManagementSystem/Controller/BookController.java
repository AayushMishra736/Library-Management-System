package com.SpringBoot.LibraryManagementSystem.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.LibraryManagementSystem.Config.BatchConfiguration;
import com.SpringBoot.LibraryManagementSystem.Entity.Book;
import com.SpringBoot.LibraryManagementSystem.Service.BookService;

@RestController

public class BookController {

	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookservice;

	@PostMapping("/books/{libId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String saveBook(@PathVariable Long libId, @RequestBody Book book) {
		log.info("Adding book through json in bookController.");
		bookservice.saveBook(libId, book);
		return "Book Added Successfully";
	}

	@GetMapping("/getAllBook")
	public List<Book> findAllBooks() {
		log.info("Fetching All Books Available in bookController.");
		return bookservice.getAllBook();
	}

	@GetMapping("/BookByName/{name}")
	public List<Book> findBookBybook_Name(@PathVariable String name) {
		log.info("Fetching book By Name in bookController.");
		return bookservice.findBookBybook_Name(name);
	}

	@GetMapping("/BookByAuthorName/{name}")
	public List<Book> findBookByAuthorName(@PathVariable String name) {
		log.info("Fetching book By Author in bookController.");
		return bookservice.getsBookByAuthorName(name);
	}

	@GetMapping("/BookByPublication/{name}")
	public List<Book> findBookByPublication(@PathVariable String name) {
		log.info("Fetching book By Publication in bookController.");
		return bookservice.getsBookPublication(name);
	}

	@PutMapping("/updateBook")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Book updateProduct(@RequestBody Book book) {
		log.info("Updating book details in bookController.");
		return bookservice.updateBook(book);

	}

	@DeleteMapping("/deleteBookByName/{name}/{id}")
	public String deleteBook(@PathVariable String name, @PathVariable Long id) {
		log.info("Deleting book details in bookController.");
		bookservice.deleteBook(name, id);
		return "Book removed successfully";
	}

}
