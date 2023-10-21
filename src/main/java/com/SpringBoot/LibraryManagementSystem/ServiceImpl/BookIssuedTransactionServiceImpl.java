package com.SpringBoot.LibraryManagementSystem.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.LibraryManagementSystem.Entity.Book;
import com.SpringBoot.LibraryManagementSystem.Entity.BookIssuedTransaction;
import com.SpringBoot.LibraryManagementSystem.Entity.Users;
import com.SpringBoot.LibraryManagementSystem.Repository.BookIssuedTransactionRepository;
import com.SpringBoot.LibraryManagementSystem.Repository.BookRepository;
import com.SpringBoot.LibraryManagementSystem.Repository.UsersRepository;
import com.SpringBoot.LibraryManagementSystem.Service.BookIssuedTransactionService;

@Service
public class BookIssuedTransactionServiceImpl implements BookIssuedTransactionService {
	
	private static final Logger log = LoggerFactory.getLogger(BookIssuedTransactionServiceImpl.class);
	
	@Autowired
	private BookIssuedTransactionRepository bookTransactionRepository;
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private BookRepository  bookRepository;
	
	public BookIssuedTransaction saveTransaction(BookIssuedTransaction bookIssuedTransaction,long userId,long bookId,long libId){
		log.info("Book issued service called for save transaction.");
		Users user = userRepository.findById(userId).get();
		Users librId = userRepository.findById(libId).get();
		Book book = bookRepository.findById(bookId).get();
		bookIssuedTransaction.setBookId(book);
		bookIssuedTransaction.setIssued_To(user);
		bookIssuedTransaction.setIssued_By(user);
		bookIssuedTransaction.setModifiedBy(user);
		bookIssuedTransaction.setCreatedBy(librId);
		bookIssuedTransaction.setUserId(user);
	    return bookTransactionRepository.save(bookIssuedTransaction);
	}
	
	public List<BookIssuedTransaction> findAllBookIssue(){
		log.info("Book issued service called for fetching all transaction.");
		return bookTransactionRepository.findAll();
	}
	
	public Optional<BookIssuedTransaction> findTransactionByTransactionId(Long id){
		log.info("Book issued service called for finding transaction by id.");
		return bookTransactionRepository.findById(id);
	}
	
	public List<BookIssuedTransaction> findTransactionByStatus(String name){	
		log.info("Book issued service called for finding transaction by name.");
		return bookTransactionRepository.findBytransactionStatus(name);
	}
	
	public BookIssuedTransaction updateTransaction(BookIssuedTransaction bookIssuedTransaction) {
		log.info("Book issued service called for updating transaction.");
		BookIssuedTransaction existingProduct = bookTransactionRepository.findById(bookIssuedTransaction.getTransactionId()).orElse(null);
		existingProduct.setBookActualreturndate(bookIssuedTransaction.getBookActualreturndate());
		existingProduct.setBookIssueddate(bookIssuedTransaction.getBookIssueddate());
		existingProduct.setBookReturndate(bookIssuedTransaction.getBookReturndate());
		existingProduct.setCreatedOn(bookIssuedTransaction.getCreatedOn());
		existingProduct.setIssued_By(bookIssuedTransaction.getIssued_By());
		existingProduct.setRemarks(bookIssuedTransaction.getRemarks());
		existingProduct.setTransactionStatus(bookIssuedTransaction.getTransactionStatus());
		existingProduct.setIssued_To(bookIssuedTransaction.getIssued_To());
		existingProduct.setUserId(bookIssuedTransaction.getUserId());
		existingProduct.setBookId(bookIssuedTransaction.getBookId());
		existingProduct.setCreatedBy(bookIssuedTransaction.getCreatedBy());
		existingProduct.setModifiedBy(bookIssuedTransaction.getModifiedBy());
		existingProduct.setModifiedon(bookIssuedTransaction.getModifiedon());
		return bookTransactionRepository.save(existingProduct);		
	}
}

