package com.example.hr.document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;

import lombok.Data;

@Document(collection="employees")
@Data
public class EmployeeDocument {
	@Id
	private String identity;
	@NotBlank
	@Field("fn")
	private String firstName;
	@NotBlank
	@Field("ln")
	private String lastName;
	@Iban
	@Field("i")
	@Indexed(unique = true)
	private String iban;
	@Min(4500)
	@Field("s")
	private double salary;
	@NotNull
	private FiatCurrency currency;
	@Max(2006)
	@Field("by")
	private int birthYear;
	@NotNull
	@Field("d")
	private Department department;
	@NotNull
	private JobStyle jobStyle;
	private String photo;
	
}
