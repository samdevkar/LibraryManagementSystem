package com.lib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lib.model.Book;

public interface BookRepository extends JpaRepository<Book,Integer>{
	
	
	@Query("from Book where name=:name")
	public List<Book>finByName(@Param("name") String name);

	
	@Query("from Book where genre=:genre")
	public List<Book> findByGenre(@Param("genre") String genre);


	@Query("from Book where author=:author_name")
	public List<Book> findByAuthor(@Param("author_name") String author_name);

}
