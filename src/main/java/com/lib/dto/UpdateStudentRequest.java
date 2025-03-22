package com.lib.dto;

import com.lib.model.NewStudent;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateStudentRequest {
	
	
	@NotBlank
	private String name;
	@NotBlank
	private String email;
	
	public NewStudent toUpdateStudent() {
		return NewStudent.builder().name(this.name).email(this.email).build();
	}
	

}
