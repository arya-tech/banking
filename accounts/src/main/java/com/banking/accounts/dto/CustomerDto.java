package com.banking.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDto {
	
	@NotEmpty(message = "Name cannot be null or empty")
	@Size(min = 5, max = 30,message = "The name of the customer name should be between 5 to 30")
	private String name;
	
	@NotEmpty(message = "email address cannot be null or empty")
	@Email(message = "Email address should be valid value")
	private String email;
	
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digit")
	private String mobileNumber;
	
	private AccountsDto accountsDto;

}
