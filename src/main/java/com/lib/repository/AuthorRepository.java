package com.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lib.model.Author;
import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer>{
	
	@Query("from Author where email = :emailId")
	public Author findByEmailId(@Param("emailId") String emailId);
	

}
