package com.lib.dto;

import com.lib.model.NewStudent;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CreateStudentRequest {
	
	@NotBlank
	private String name;
	@NotBlank
	private String email;
	@NotBlank
	private String rollNumber;
	
	public NewStudent toStudent() {
		return NewStudent.builder().name(this.name).email(this.email).rollNumber(this.rollNumber).build();
	}

}
