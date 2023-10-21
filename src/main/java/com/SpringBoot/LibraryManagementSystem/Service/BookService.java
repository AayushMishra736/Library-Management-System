package com.SpringBoot.LibraryManagementSystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SpringBoot.LibraryManagementSystem.Entity.Book;

@Service
public interface BookService {


	public Book saveBook(Long libId, Book book) ;

	public List<Book> getAllBook();

	public Book getsBookById(Long id);

	public List<Book> findBookBybook_Name(String name);

	public List<Book> getsBookByAuthorName(String author);
	public List<Book> getsBookPublication(String name);

	public Book deleteBook(String name, Long id);

	public Book updateBook(Book book);
}
