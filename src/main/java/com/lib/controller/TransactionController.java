package com.lib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lib.dto.InitiateTransaction;
import com.lib.service.TransactionService;

import jakarta.validation.Valid;

@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/transaction")
	public void initiateTransaction(@RequestBody @Valid InitiateTransaction initiateTransaction) throws Exception {
	
		transactionService.initiateTransaction(initiateTransaction);
	}

}
