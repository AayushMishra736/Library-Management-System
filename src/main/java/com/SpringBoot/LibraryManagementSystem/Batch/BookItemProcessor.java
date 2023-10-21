package com.SpringBoot.LibraryManagementSystem.Batch;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.SpringBoot.LibraryManagementSystem.Entity.Book;
import com.SpringBoot.LibraryManagementSystem.Entity.Users;



@Component
public class BookItemProcessor implements ItemProcessor<Book,Book>{

	public static final Logger log =  LoggerFactory.getLogger(BookItemProcessor.class);
	
	private Users user;
	
	@SuppressWarnings("static-access")
	@Override
	public Book process(Book books) throws Exception {
		log.info("Book Process start");
		books.setModifiedOn(new Date());
		books.setCreatedOn(new Date());
		books.setActiveFlag(1);
		log.info("--------------------"+books);
		log.info("Book Process stop");
		return books;
	}
}








