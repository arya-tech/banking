package com.banking.accounts.service.impl;

import org.springframework.stereotype.Service;

import com.banking.accounts.dto.CustomerDto;
import com.banking.accounts.repository.AccountRepository;
import com.banking.accounts.repository.CustomerRepository;
import com.banking.accounts.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IAccountServiceImpl implements IAccountService {
	
	
	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;

	@Override
	public void createAccount(CustomerDto customerDto) {
		
		
		
	}

}
