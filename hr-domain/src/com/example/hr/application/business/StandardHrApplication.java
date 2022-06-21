package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.domain.event.EmployeeFiredEvent;
import com.example.hr.domain.event.EmployeeHiredEvent;
import com.example.hr.domain.event.HrEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private EmployeeRepository employeeRepository;
	private EventPublisher<HrEvent> eventPublisher;
	
	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher<HrEvent> eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var identity = employee.getTcKimlikNo();
		if (employeeRepository.findEmployeeByIdentity(identity).isPresent())
			throw new ExistingEmployeeException(identity);
		Employee hiredEmployee = employeeRepository.insert(employee);
		eventPublisher.publishEvent(new EmployeeHiredEvent(identity));
		return hiredEmployee;
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo identity) {
		Optional<Employee> firedEmployee = employeeRepository.remove(identity);
		if (firedEmployee.isPresent())
			eventPublisher.publishEvent(new EmployeeFiredEvent(identity));			
		return firedEmployee;
	}

	@Override
	public Optional<Employee> getEmployeeInformation(TcKimlikNo identity) {
		return employeeRepository.findEmployeeByIdentity(identity);
	}

}
