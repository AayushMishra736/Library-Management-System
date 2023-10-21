package com.SpringBoot.LibraryManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import com.SpringBoot.LibraryManagementSystem.Entity.BookIssuedTransaction;

public interface BookIssuedTransactionService {

	
	public BookIssuedTransaction saveTransaction(BookIssuedTransaction bookIssuedTransaction,long userId,long bookId,long libId);
	
	public List<BookIssuedTransaction> findAllBookIssue();
	
	public Optional<BookIssuedTransaction> findTransactionByTransactionId(Long id);
	
	public List<BookIssuedTransaction> findTransactionByStatus(String name);
	
	public BookIssuedTransaction updateTransaction(BookIssuedTransaction bookIssuedTransaction);
	
}
