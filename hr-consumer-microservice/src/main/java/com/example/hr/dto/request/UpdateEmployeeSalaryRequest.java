package com.example.hr.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateEmployeeSalaryRequest {
	private double rate;

	public UpdateEmployeeSalaryRequest(double rate) {
		this.rate = rate;
	}
	
}
