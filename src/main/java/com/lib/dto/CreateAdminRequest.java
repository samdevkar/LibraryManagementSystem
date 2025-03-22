package com.lib.dto;

import com.lib.model.Admin;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAdminRequest {
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Column(unique = true)
	private String email;
	
	public Admin toAdmin() {
		return Admin.builder()
				.name(this.name)
				.email(this.email)
				.build();
	}
	
	

}
