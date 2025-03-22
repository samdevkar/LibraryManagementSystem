package com.lib.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lib.dto.InitiateTransaction;
import com.lib.model.Admin;
import com.lib.model.Book;
import com.lib.model.NewStudent;
import com.lib.model.Transaction;
import com.lib.model.TransactionStatus;
import com.lib.model.TransactionType;
import com.lib.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	AdminService adminService;
	@Autowired
	AuthorService authorService;
	@Autowired
	BookService bookService;
	@Autowired
	StudentService studentService;
	@Autowired
	TransactionRepository transactionRepository;
	
	@Value("${student.allowed.max-book}")
	private Integer maxBookAllowed;
	@Value("${student.allowed.max-duration}")
	private Integer allowedDuration;
	
	public String initiateTransaction(InitiateTransaction initiateTransaction) throws Exception {
		return initiateTransaction.getTransactionType()==TransactionType.RETURN
				? returnBook(initiateTransaction):issueBook(initiateTransaction);
				
	}

	private String issueBook(InitiateTransaction initiateTransaction) throws Exception {
		List<NewStudent>studentList=studentService.findStudent("rollNumber",initiateTransaction.getRollNumber());
		NewStudent student=studentList.size()>0 ?studentList.get(0):null;
		
		List<Book>bookList=bookService.findBooks("id",initiateTransaction.getBookId().toString());
		Book book=bookList.size()>0 ? bookList.get(0):null;
		
		Admin admin=adminService.findAdmin(initiateTransaction.getAdminId());
		
		if(student==null || book==null || admin==null) {
			throw new Exception("Invalid request");
		}
		if(student.getBookList().size()>maxBookAllowed) {
			throw new Exception("Max limit reached");
		}
		if(book.getNewstudent()!=null) {
			throw new Exception("Book Already Issued to Student "+book.getNewstudent().getName());
		}
		Transaction transaction=null;
		
		try {
			transaction=Transaction.builder()
					    .tranctionId(UUID.randomUUID().toString())
					    .admin(admin)
					    .newstudent(student)
					    .book(book)
					    .transactionType(TransactionType.ISSUE)
					    .transactionStatus(TransactionStatus.PENDING)
					    .build();
			
			transactionRepository.save(transaction);
			book.setNewstudent(student);
			bookService.createBook(book);
			transaction.setTransactionStatus(TransactionStatus.SUCCESS);
			
		} catch(Exception e) {
			transaction.setTransactionStatus(TransactionStatus.FAILURE);
			
		}finally {
			transactionRepository.save(transaction);
			
		}
		return transaction.getTranctionId();
	}

	private String returnBook(InitiateTransaction initiateTransaction) throws Exception {
		List<NewStudent>studentList=studentService.findStudent("rollNumber",initiateTransaction.getRollNumber());
		NewStudent student=studentList.size()>0 ?studentList.get(0):null;
		
		List<Book>bookList=bookService.findBooks("id",initiateTransaction.getBookId().toString());
		Book book=bookList.size()>0 ? bookList.get(0):null;
		
		Admin admin=adminService.findAdmin(initiateTransaction.getAdminId());
		
		if(student==null || book==null || admin==null) {
			throw new Exception("Invalid request");
		}
		
		if(book.getNewstudent()==null || !book.getNewstudent().getId().equals(student.getId())) {
			throw new Exception("Book is Not issued to this Student");
		}
		List<Transaction> issuenceTransaction = transactionRepository.findByNewstudent_IdAndBook_IdAndTransactionType(student.getId(),book.getId(),TransactionType.RETURN);
		
		if(issuenceTransaction==null) {
			throw new Exception("Book is Not Issued");
		}
		Transaction transaction=null;
		try {
			transaction=Transaction.builder()
					.tranctionId(UUID.randomUUID().toString())
					.admin(admin)
					.book(book)
					.newstudent(student)
					.transactionStatus(TransactionStatus.PENDING)
					.transactionType(TransactionType.RETURN)
					.build();
					
			transactionRepository.save(transaction);
			book.setNewstudent(null);
			bookService.createBook(book);
			transaction.setTransactionStatus(TransactionStatus.SUCCESS);
		}catch(Exception e) {
			transaction.setTransactionStatus(TransactionStatus.FAILURE);
		}finally {
			transactionRepository.save(transaction);
		}
		return transaction.getTranctionId();
		
	
	}
}
