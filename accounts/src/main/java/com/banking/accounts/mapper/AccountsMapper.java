package com.banking.accounts.mapper;

import com.banking.accounts.dto.AccountsDto;
import com.banking.accounts.entites.Accounts;

public class AccountsMapper {

	public static AccountsDto mapToAccountsdto(Accounts accounts, AccountsDto accountsDto) {
		accountsDto.setAccountType(accounts.getAccountType());
		accountsDto.setAccountNumber(accounts.getAccountNumber());
		accountsDto.setBranchAddress(accounts.getBranchAddress());
		return accountsDto;
	}

	public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
		accounts.setAccountType(accountsDto.getAccountType());
		accounts.setAccountNumber(accountsDto.getAccountNumber());
		accounts.setBranchAddress(accountsDto.getBranchAddress());
		return accounts;
	}

}
