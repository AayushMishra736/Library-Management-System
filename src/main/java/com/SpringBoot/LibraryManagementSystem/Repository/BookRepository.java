package com.SpringBoot.LibraryManagementSystem.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.SpringBoot.LibraryManagementSystem.Entity.Book;



@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	 public List<Book> findByName(String name);
	 

	 public List<Book> findByauthor(String author);
	 

	 public List<Book> findBypublication(String name);
	
}
