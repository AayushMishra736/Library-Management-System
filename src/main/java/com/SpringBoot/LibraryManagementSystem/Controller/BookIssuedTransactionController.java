package com.SpringBoot.LibraryManagementSystem.Controller;

import java.util.List;
import java.util.Optional;

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
import com.SpringBoot.LibraryManagementSystem.Entity.BookIssuedTransaction;
import com.SpringBoot.LibraryManagementSystem.Service.BookIssuedTransactionService;

@RestController
public class BookIssuedTransactionController {

	private static final Logger log = LoggerFactory.getLogger(BookIssuedTransactionController.class);
	
	@Autowired
	private BookIssuedTransactionService bookissuedservice;

	@PostMapping("/Transaction/{bookId}/{userId}/{libId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String saveTransaction(@RequestBody BookIssuedTransaction transaction,
			@PathVariable("userId")Long userId, @PathVariable("bookId")Long bookId, @PathVariable("userId")Long libId)  {
		log.info("Book issued controller called for save transaction.");
		bookissuedservice.saveTransaction(transaction,userId,bookId,libId);
		return "Transaction Occur Successfully";
	}

	@GetMapping("/getAllTransaction")
	public List<BookIssuedTransaction> findAllTransaction() {
		log.info("Book issued controller called for fetching all transaction.");
		return bookissuedservice.findAllBookIssue();
	}

	@GetMapping("/getTransactionByTransactionId/{id}")
	public Optional<BookIssuedTransaction> getTransactionByTransactionId(@PathVariable Long id) {
		log.info("Book issued controller called for fetching tranaction by transaction id.");
		return bookissuedservice.findTransactionByTransactionId(id);
	}

	@GetMapping("/getTransactionByStatus/{name}")
	public List<BookIssuedTransaction> getTransactionByStatus(@PathVariable String name) {
		log.info("Book issued controller called for fetching transaction by status.");
		return bookissuedservice.findTransactionByStatus(name);
	}

	@PutMapping("/updateTransaction")
	public BookIssuedTransaction updateTransaction(@RequestBody BookIssuedTransaction book) {
		log.info("Book issued controller called for updating transaction ");
		return bookissuedservice.updateTransaction(book);

	}

}
