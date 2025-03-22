package com.lib.dto;

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
public class CreateSearchRequest {
	
	@NotBlank
	private String searchKey;
	@NotBlank
	private String searchValue;

}
