package com.example.hr.domain;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EntityClass(identity = "tcKimlikNo")
@Aggregate // Aggregate ==> Entity Root
@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = "photo")
@EqualsAndHashCode(of = "tcKimlikNo")
@Builder
public class EmployeeLombok {
	private final TcKimlikNo tcKimlikNo;
	private FullName fullName;
	private Iban iban;
	private Money salary;
	private final BirthYear birthYear;
	private Photo photo;
	private JobStyle jobStyle;
	private Department department;


}