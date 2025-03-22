package com.lib.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.model.Author;
import com.lib.model.Book;
import com.lib.repository.BookRepository;

@Service
public class BookService {
	 @Autowired
     private BookRepository bookRepository;
	 
	 @Autowired
	 private AuthorService authorService;
	 
	 public void createBook(Book book) {
		 Author existing_author=authorService.getOrCreate(book.getAuthor());
		 book.setAuthor(existing_author);
		 bookRepository.save(book);
	 }
	 
	 public List<Book>findBooks(String searchKey,String searchValue) throws Exception{
		 switch(searchKey) {
		     case "name" -> {
		    	 return bookRepository.finByName(searchValue);
		     }
		     case "genre" ->{
		    	 return bookRepository.findByGenre(searchValue);
		     }
		  
		     case "id" -> {
		    	 Optional<Book>book= bookRepository.findById(Integer.parseInt(searchValue));
		    	 return book.map(Arrays::asList).orElseGet(ArrayList::new);
		     }
		     default -> throw new Exception("Search Key is Not Valid"+searchKey);
		 
		 }
	 }
	 
	 public List<Book>getAllBooks(){
		 return bookRepository.findAll();
	 }
}
