package com.banking.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.accounts.entites.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
