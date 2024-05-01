package com.banking.accounts.entites;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass // indicating this is the super class for remaining entity classes
public class BaseEntity {

	@Column(updatable = false) // it will tell db , whenever record updated dont change the value of this column
	private LocalDateTime createdAt;

	@Column(updatable = false)
	private String createdBy;

	@Column(insertable = false) //it will tell db , whenever record newly inserted dont change the value of this column
	private LocalDateTime updatedAt;

	@Column(insertable = false)
	private String updatedBy;

}
