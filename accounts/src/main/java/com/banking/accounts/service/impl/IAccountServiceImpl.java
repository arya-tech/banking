package com.banking.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.banking.accounts.constants.AccountsConstants;
import com.banking.accounts.dto.AccountsDto;
import com.banking.accounts.dto.CustomerDto;
import com.banking.accounts.entites.Accounts;
import com.banking.accounts.entites.Customer;
import com.banking.accounts.exception.CustomerAlreadyExistsException;
import com.banking.accounts.exception.ResourseNotFoundException;
import com.banking.accounts.mapper.AccountsMapper;
import com.banking.accounts.mapper.CustomerMapper;
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
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		//customer.setCreatedAt(LocalDateTime.now());
		//customer.setCreatedBy("Admin");
		// validating customer already exist with given mobile number

		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if (optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException(
					"Customer already registered with given mobile number " + customerDto.getMobileNumber());
		}
		Customer savedCustomer = customerRepository.save(customer);

		accountRepository.save(createNewAccount(savedCustomer));
	}

	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		Long randomAccNumber = 1000000000L + new Random().nextInt(900000000);// generating random account number for
																				// customer
		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		//newAccount.setCreatedAt(LocalDateTime.now());
		//newAccount.setCreatedBy("Admin");
		return newAccount;
	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourseNotFoundException("Customer", "Mobile Number", mobileNumber));

		Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourseNotFoundException("Account", "Customer ID", customer.getCustomerId().toString()));
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsdto(accounts, new AccountsDto()));
		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccountsDto();
		if (accountsDto != null) {
			Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber())
					.orElseThrow(() -> new ResourseNotFoundException("Account", "Account Number",
							accountsDto.getAccountNumber().toString()));

			Accounts accountMapper = AccountsMapper.mapToAccounts(accountsDto, accounts);
			Accounts savedAccounts = accountRepository.save(accountMapper);

			Customer customer = customerRepository.findById(savedAccounts.getCustomerId())
					.orElseThrow(() -> new ResourseNotFoundException("Customer", "Customer ID",
							savedAccounts.getCustomerId().toString()));

			Customer customerMapper = CustomerMapper.mapToCustomer(customerDto, customer);
			customerRepository.save(customerMapper);
			isUpdated = true;

		}

		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {

		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourseNotFoundException("Customer", "Mobile Number", mobileNumber));
		accountRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		return true;
	}

}
