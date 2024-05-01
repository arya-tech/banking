package com.banking.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.accounts.entites.Accounts;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {

}
