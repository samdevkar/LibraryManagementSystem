package com.lib.dto;

import com.lib.model.Author;
import com.lib.model.Book;
import com.lib.model.Genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {
	
	@NotBlank
	private String name;
	@NotNull
	private Genre genre;
	@NotBlank
	private String authorName;
	@NotBlank
	private String authorEmail;
	

	public Book toBook() {
		
		Author authorref=Author.builder().name(this.authorName).email(authorEmail).build();
		
		return Book.builder().name(this.name).genre(this.genre).author(authorref).build();
	}
	

}
