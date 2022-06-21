package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

@Service
public class HrService {
	private HrApplication hrApplication;
	private ModelMapper modelMapper;

	public HrService(HrApplication hrApplication, ModelMapper modelMapper) {
		this.hrApplication = hrApplication;
		this.modelMapper = modelMapper;
	}

	@Transactional
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var employee = modelMapper.map(request, Employee.class);
		hrApplication.hireEmployee(employee);
		return new HireEmployeeResponse("success");
	}

	@Transactional
	public FireEmployeeResponse fireEmployee(String identity) {
		TcKimlikNo kimlik = TcKimlikNo.valueOf(identity);
		var firedEmployee = hrApplication.fireEmployee(kimlik);
		if (firedEmployee.isEmpty())
			throw new EmployeeNotFoundException(kimlik);
		return new FireEmployeeResponse("success");
	}

	public EmployeeResponse findEmployee(String identity) {
		TcKimlikNo kimlik = TcKimlikNo.valueOf(identity);
		return hrApplication.getEmployeeInformation(kimlik)
				            .map( emp -> modelMapper.map(emp, EmployeeResponse.class))
				            .orElseThrow( () -> new EmployeeNotFoundException(kimlik));
	}

}
