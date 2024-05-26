package com.banking.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.accounts.constants.AccountsConstants;
import com.banking.accounts.dto.CustomerDto;
import com.banking.accounts.dto.ResponseDto;
import com.banking.accounts.service.IAccountService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {

	// autowiring with constructor argument which is recommanded approach
	private IAccountService accountService;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		accountService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_200));
	}

	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digit") @RequestParam String mobileNumber) {
		CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
		return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
		boolean isUpdated = accountService.updateAccount(customerDto);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
		}

	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccountDetails(
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digit") @RequestParam String mobileNumber) {
		boolean isDeleted = accountService.deleteAccount(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
		}

	}

}
