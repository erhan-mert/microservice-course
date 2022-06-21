package com.example.hr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import lombok.Data;

@Entity
@Table(name="employees")
@DynamicUpdate
@DynamicInsert
@Data
public class EmployeeEntity {
	@TcKimlikNo
	@Id
	private String identity;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Iban
	private String iban;
	@Min(4500)
	private double salary;
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private FiatCurrency currency;
	@Max(2006)
	private int birthYear;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Department department;
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private JobStyle jobStyle;
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] photo;	
}
