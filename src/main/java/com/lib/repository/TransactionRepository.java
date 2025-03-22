package com.lib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lib.model.Transaction;
import com.lib.model.TransactionType;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
//	@Query("from Transaction t where t.newStudent.id=:s_id and t.book.id=:b_id and t.transactionType=:transactionType")
//	public List<Transaction> findTransactionByStudentAndBookAndTransactionTypeOrderByDesc(Integer s_id,Integer b_id,TransactionType transactionType);

    List<Transaction> findByNewstudent_IdAndBook_IdAndTransactionType(
            Integer studentId,
            Integer bookId,
            TransactionType transactionType
        );

}
