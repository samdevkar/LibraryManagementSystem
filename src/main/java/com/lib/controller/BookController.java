package com.lib.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lib.dto.CreateBookRequest;
import com.lib.dto.CreateSearchRequest;
import com.lib.model.Book;
import com.lib.service.BookService;

import jakarta.validation.Valid;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/createbook")
	public void createBook(@RequestBody @Valid CreateBookRequest createBookRequest) {
		bookService.createBook(createBookRequest.toBook());
	}
	@GetMapping("/getbooklist")
	public List<Book> getBooks(@RequestBody @Valid CreateSearchRequest createSearchRequest) throws Exception {
		return bookService.findBooks(createSearchRequest.getSearchKey(), createSearchRequest.getSearchValue());
	}
	@GetMapping("/getAllBooks")
	public List<Book>getAllBooks(){
		return bookService.getAllBooks();
	}

}
