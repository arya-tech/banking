package com.banking.accounts.entites;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass // indicating this is the super class for remaining entity classes
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

	@CreatedDate
	@Column(updatable = false) // it will tell db , whenever record updated dont change the value of this column
	private LocalDateTime createdAt;

	@CreatedBy
	@Column(updatable = false)
	private String createdBy;

	@LastModifiedDate
	@Column(insertable = false) //it will tell db , whenever record newly inserted dont change the value of this column
	private LocalDateTime updatedAt;

	@LastModifiedBy
	@Column(insertable = false)
	private String updatedBy;

}
