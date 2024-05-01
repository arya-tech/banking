package com.banking.accounts.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Accounts extends BaseEntity {

	@Id // we are writing logic to generate account number (it should be 10 digit long)
	private Long accountNumber;

	private Long customerId;

	private String accountType;

	private String branchAddress;

}
