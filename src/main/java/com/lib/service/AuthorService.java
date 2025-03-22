package com.lib.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.model.Author;
import com.lib.repository.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	
	public Author getOrCreate(Author author) {
		Author existing_author=authorRepository.findByEmailId(author.getEmail());
		
		if(existing_author==null) {
			existing_author=authorRepository.save(author);
		}
		return existing_author;
		
	}
	
	public Author findAdmin(Integer id) {
		Optional<Author>author= authorRepository.findById(id);
		return author.get();
	}
	
}
