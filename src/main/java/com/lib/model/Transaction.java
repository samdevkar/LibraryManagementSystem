package com.lib.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String tranctionId;
	
	@CreationTimestamp
	private Date createdOn;
	
	@ManyToOne
	@JoinColumn(name = "newstudent_id")
	private NewStudent newstudent;
	
	@Enumerated(value=EnumType.STRING)
	private TransactionType transactionType;
	
	
	@Enumerated(value=EnumType.STRING)
	private TransactionStatus transactionStatus;
	
	@ManyToOne
	@JoinColumn
	private Book book;

	@ManyToOne
	@JoinColumn
	private Author author;
	
	@ManyToOne
	@JoinColumn
	private Admin admin;
	

}
