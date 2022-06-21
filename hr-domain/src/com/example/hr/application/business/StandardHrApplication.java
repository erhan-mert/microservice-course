package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.exception.EmployeeNotFoundException;
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
		if (employeeRepository.findEmployeeByIdentity(identity).isPresent()) {
			
			var exception = new ExistingEmployeeException(identity);
			exception.setDebugId("fa94ad1e-f160-11ec-8ea0-0242ac120002");
			throw exception;
		}
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

	//Shift + Alt + L
	@Override
	public Optional<Employee> updateSalary(TcKimlikNo identity, double rate) {
		Optional<Employee> foundEmployee = employeeRepository.findEmployeeByIdentity(identity);
		if (foundEmployee.isEmpty()) {
			throw new EmployeeNotFoundException(identity);
		}
		var employee = foundEmployee.get();
		employee.increaseSalary(rate);
		employeeRepository.update(employee);
		return Optional.of(employee);
	}

}
