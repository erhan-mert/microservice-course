package com.example.crm.document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "customers")
@Data
public class CustomerDocument {
	@Id
	private String customerId;
	private String fullName;
	@Email
	@Indexed(unique = true)
	private String email;
	@Pattern(regexp = "^\\+(\\d+[-]?)+$")
	private String phone;
	private int birthYear;
}
