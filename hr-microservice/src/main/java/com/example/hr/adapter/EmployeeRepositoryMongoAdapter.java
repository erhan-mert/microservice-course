package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeDocumentRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
@ConditionalOnProperty(name = "database", havingValue = "mongodb")
public class EmployeeRepositoryMongoAdapter implements EmployeeRepository {
	private final EmployeeDocumentRepository employeeDocumentRepository;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryMongoAdapter(EmployeeDocumentRepository employeeDocumentRepository,
			ModelMapper modelMapper) {
		this.employeeDocumentRepository = employeeDocumentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity) {
		var kimlik = identity.getValue();
		return employeeDocumentRepository.findById(kimlik)
		                                 .map(document -> modelMapper.map(document, Employee.class));
	}

	@Override
	public Employee insert(Employee employee) {
		var employeeDocument = modelMapper.map(employee, EmployeeDocument.class);
		return modelMapper.map(employeeDocumentRepository.insert(employeeDocument), Employee.class);
	}

	@Override
	public Optional<Employee> remove(TcKimlikNo identity) {
		var kimlik = identity.getValue();
		var document = employeeDocumentRepository.findById(kimlik)
				.orElseThrow( () -> new EmployeeNotFoundException(identity));
		employeeDocumentRepository.delete(document);
		return Optional.of(modelMapper.map(document, Employee.class));
	}

	@Override
	public void update(Employee employee) {
		var document = modelMapper.map(employee, EmployeeDocument.class);
		employeeDocumentRepository.save(document);
	}

}
