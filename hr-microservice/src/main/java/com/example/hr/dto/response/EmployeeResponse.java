package com.example.hr.dto.response;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeResponse {
	private String identity;
	private String firstName;
	private String lastName;
	private String iban;
	private double salary;
	private FiatCurrency currency;
	private int birthYear;
	private Department department;
	private JobStyle jobStyle;
	private String photo;
}
