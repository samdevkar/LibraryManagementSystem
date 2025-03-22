package com.lib.dto;

import com.lib.model.TransactionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InitiateTransaction {
	
	@NotBlank
	private String rollNumber;
	@NotNull
	private Integer bookId;
	@NotNull
	private Integer adminId;
	@NotNull
	private TransactionType transactionType;

}
