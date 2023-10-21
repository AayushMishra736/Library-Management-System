package com.SpringBoot.LibraryManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.SpringBoot.LibraryManagementSystem.Entity.BookIssuedTransaction;


@Repository
public interface BookIssuedTransactionRepository extends JpaRepository<BookIssuedTransaction, Long> {

    List<BookIssuedTransaction> findByuserId(Long userId);
    List<BookIssuedTransaction> findBytransactionStatus(String transactionStatus);
}

