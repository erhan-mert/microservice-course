package com.example.hr.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEmployeeSalaryRequest {
	@Min(0)
	@Max(10)
	private double rate;
}
