package com.SpringBoot.LibraryManagementSystem.Service;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.error.OptionalShouldContainInstanceOf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.SpringBoot.LibraryManagementSystem.Entity.Book;
import com.SpringBoot.LibraryManagementSystem.Entity.BookIssuedTransaction;
import com.SpringBoot.LibraryManagementSystem.Entity.Users;
import com.SpringBoot.LibraryManagementSystem.Repository.BookIssuedTransactionRepository;
import com.SpringBoot.LibraryManagementSystem.ServiceImpl.BookIssuedTransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookIssuedTransactionServiceTest {
	
	@Mock
	private BookIssuedTransactionRepository bookIssueRepository;
	
	@InjectMocks
	private BookIssuedTransactionServiceImpl bookIssueService;
	
	
	@DisplayName("Junit test for save transaction.")
	@Test
	public void givenBookIssueObject_whenSave_ThenReturnBookIssueObject() {
		BookIssuedTransaction bookIssue = BookIssuedTransaction.builder().
				transactionId(1L).bookIssueddate(new Date()).transactionStatus("Approved").
				remarks("Book Issued").build();
		BDDMockito.given(bookIssueRepository.findById(1L)).willReturn(Optional.of(bookIssue));
		BDDMockito.given(bookIssueRepository.save(bookIssue)).willReturn(bookIssue);
        BookIssuedTransaction savedBook = bookIssueService.saveTransaction(bookIssue,2L,1L,1L);
       	assertThat(savedBook).isNotNull();
	}
	
	
	@DisplayName("Junit test for get book issue by transaction id")
	@Test
	public void givenBookIssueObject_whenFindById_thenReturnBookIssueObject() {
		BookIssuedTransaction	bookIssue1 = BookIssuedTransaction.builder().transactionId(1L).bookIssueddate(new Date()).transactionStatus("Approved").remarks("Book Issued").build();
		BDDMockito.given(bookIssueRepository.findById(bookIssue1.getTransactionId())).willReturn(Optional.of(bookIssue1));
		BookIssuedTransaction savedBookIssue = bookIssueService.findTransactionByTransactionId(bookIssue1.getTransactionId()).get();
		assertThat(savedBookIssue).isNotNull();
	}

	@DisplayName("Junit test for getting all books.")
	@Test
	public void givenBookIssueObject_whenFindall_thenReturnBookIssueObject() {
		BookIssuedTransaction	bookIssue1 =
		BookIssuedTransaction.builder().transactionId(1L).bookIssueddate(new Date()).
		transactionStatus("Approved").remarks("Book Issued").build();
		BDDMockito.given(bookIssueRepository.findAll()).willReturn(List.of(bookIssue1));
		List<BookIssuedTransaction> bookList = bookIssueService.findAllBookIssue();
		assertThat(bookList).isNotNull();
		assertThat(bookList.size()).isGreaterThan(0);
	}

	@DisplayName("Junit test for finding transaction by transaction status.")
	@Test
	public void givenBookIssueObject_whenFindByStatus_thenReturnBookIssueObject() {
		BookIssuedTransaction	bookIssue1 =
		BookIssuedTransaction.builder().transactionId(1L).bookIssueddate(new Date()).
		transactionStatus("Approved").remarks("Book Issued").build();
		BDDMockito.given(bookIssueRepository.findBytransactionStatus("Approved")).willReturn(List.of(bookIssue1));
		List<BookIssuedTransaction> savedBookTransaction = bookIssueService.findTransactionByStatus(bookIssue1.getTransactionStatus());
		assertThat(savedBookTransaction).isNotNull();
	}
	
	@DisplayName("Junit test for updating transaction.")
	@Test
	public void givenUserObject_ForUpdateTransaction_thenReturnUserObject() {
		BookIssuedTransaction	bookIssue1 =
				BookIssuedTransaction.builder().transactionId(1L).bookIssueddate(new Date()).
				transactionStatus("Approved").remarks("Book Issued").build();
		BDDMockito.given(bookIssueRepository.save(bookIssue1)).willReturn(bookIssue1);
		BookIssuedTransaction savedUser = bookIssueService.saveTransaction(bookIssue1,2L,1L,1L);
		savedUser.setRemarks("Book is issued and only nine left.");
        assertThat(savedUser.getRemarks()).isEqualTo("Book is issued and only nine left.");
	}
	
	
}
