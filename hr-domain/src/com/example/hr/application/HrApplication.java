package com.example.hr.application;

import java.util.Optional;

import com.example.hr.domain.Application;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Application
public interface HrApplication {
	Employee hireEmployee(Employee employee);
	Optional<Employee> fireEmployee(TcKimlikNo identity);
	Optional<Employee> getEmployeeInformation(TcKimlikNo identity);
}
