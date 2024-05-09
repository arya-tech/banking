package com.banking.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {
	
	@NotEmpty(message = "Account number cannot be null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Account number must be 10 digit")
	private Long accountNumber;
	
	@NotEmpty(message = "Account type cannot be null or empty")
	private String accountType;
	
	@NotEmpty(message = "branch address cannot be null or empty")
	private String branchAddress;

}
