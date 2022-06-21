package com.example.hr.application.business.exception;

import com.example.hr.domain.TcKimlikNo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@SuppressWarnings("serial")
@AllArgsConstructor
@Getter
@ToString
public class EmployeeNotFoundException extends RuntimeException {
	private final TcKimlikNo identity;
}
