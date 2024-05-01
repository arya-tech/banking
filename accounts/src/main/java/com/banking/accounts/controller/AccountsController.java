package com.banking.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.accounts.constants.AccountsConstants;
import com.banking.accounts.dto.CustomerDto;
import com.banking.accounts.dto.ResponseDto;

@RestController
@RequestMapping(path="/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {
	
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_200));
	}
	
	
	
	
	
	
	

}