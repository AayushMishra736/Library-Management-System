package com.SpringBoot.LibraryManagementSystem.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.SpringBoot.LibraryManagementSystem.Entity.Book;
import com.SpringBoot.LibraryManagementSystem.Entity.BookIssuedTransaction;
import com.SpringBoot.LibraryManagementSystem.Entity.Users;
import com.SpringBoot.LibraryManagementSystem.ServiceImpl.BookIssuedTransactionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = BookIssuedTransactionController.class)
public class BookIssuedTransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookIssuedTransactionServiceImpl bookIssueService;

	@Autowired
	private ObjectMapper objectMapper;
	
	private BookIssuedTransaction bookIssue;
	
	@DisplayName("Junit test for creating new bookIssue Transaction using rest api")
    @Test
    public void givenBookIssuedObject_whenInsertBookIssued_thenReturnSavedTransaction() throws Exception{        
	BookIssuedTransaction bookIssuedTransaction = BookIssuedTransaction.builder().remarks("Approved").
				transactionStatus("Transaction Approved").build();
    BDDMockito.given(bookIssueService.saveTransaction(bookIssuedTransaction, 2L, 1l, 1L)).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/Transaction/1/2/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(bookIssuedTransaction)));
        response.andDo(print()).
                andExpect(status().isCreated());

    }
	
	@DisplayName("Junit test for geting all transaction using rest api")
	@Test
	public void givenBookObjectList_whenGetAllUser_thenReturnBookList() throws Exception {
		List<BookIssuedTransaction> transactionList = new ArrayList<>();
		transactionList.add(BookIssuedTransaction.builder().remarks("Approved").
				transactionStatus("Transaction Approved").build());
		transactionList.add(BookIssuedTransaction.builder().remarks("Rejected").
				transactionStatus("Transaction Approved").build());
		BDDMockito.given(bookIssueService.findAllBookIssue()).willReturn(transactionList);
		ResultActions response = mockMvc.perform(get("/getAllTransaction"));
		response.andExpect(status().isOk());
	}
	
	@DisplayName("Junit test for get transaction by transaction id using rest api")
	@Test
	public void givenBookObject_whenGetbyName_thenReturnBookObject() throws Exception {
		BookIssuedTransaction bookIssuedTransaction = BookIssuedTransaction.builder().transactionId(1L).remarks("Approved").
				transactionStatus("Transaction Approved").build();
		BDDMockito.given(bookIssueService.findTransactionByTransactionId(1L)).willReturn(Optional.of(bookIssuedTransaction));
		ResultActions response = mockMvc.perform(get("/getTransactionByTransactionId/1", bookIssuedTransaction.getTransactionId()));
		response.andExpect(status().isOk()).andDo(print());
	}
	@DisplayName("Junit test for get transaction by transaction status using rest api")
	@Test
	public void givenBookObject_whenGetbyStatus_thenReturnBookObject() throws Exception {
		BookIssuedTransaction bookIssuedTransaction = BookIssuedTransaction.builder().transactionId(1L).remarks("Approved").
				transactionStatus("Transaction Approved").build();
		BDDMockito.given(bookIssueService.findTransactionByStatus("Approved")).willReturn(List.of(bookIssuedTransaction));
		ResultActions response = mockMvc.perform(get("/getTransactionByStatus/Approved", bookIssuedTransaction.getTransactionId()));
		response.andExpect(status().isOk()).andDo(print());
	}
	@DisplayName("Junit test for update transaction  using rest api")
	@Test
	public void givenBookObject_whenToUpdateTransaction_thenReturnBookObject() throws Exception {
		Book savedbook = Book.builder().bookId(1L)
                .activeFlag(1).author("Erica Jong").bookPrice(700).name("Fear of Flying").publication("Penguin")
                .noOfCopies(10)
                .build();
		
		BookIssuedTransaction bookIssuedTransaction = BookIssuedTransaction.builder().transactionId(1L).bookActualreturndate(new Date()).
				remarks("Approved").bookIssueddate(new Date()).bookReturndate(new Date()).bookId(savedbook).
				transactionStatus("Transaction Approved").build();
		
		BookIssuedTransaction updatedbookIssuedTransaction = BookIssuedTransaction.builder().transactionId(1L).bookActualreturndate(new Date()).
				remarks("Rejected").bookIssueddate(new Date()).bookReturndate(new Date()).bookId(savedbook).
				transactionStatus("Rejected").build();
		
		BDDMockito.given(bookIssueService.findTransactionByTransactionId(1L)).willReturn(Optional.of(bookIssuedTransaction));
		BDDMockito.given(bookIssueService.updateTransaction(updatedbookIssuedTransaction)).willReturn(updatedbookIssuedTransaction);
		ResultActions response = mockMvc.perform(put("/updateTransaction"));
		response.andExpect(status().isOk()).andDo(print());
	}
	
	
}
