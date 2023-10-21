package com.SpringBoot.LibraryManagementSystem.Entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.SpringBoot.LibraryManagementSystem.Entity.Users;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "Book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long bookId;
	private String name;
	private String author;
	private String publication;
	private int bookPrice;
	private int noOfCopies;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "createdBy",referencedColumnName = "userId")
	private Users createdBy; 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/mm/yyyy")
	private Date createdOn;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modifiedBy")
	private Users modifiedBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/mm/yyyy")
	private Date modifiedOn;
	private int activeFlag;

	
}
