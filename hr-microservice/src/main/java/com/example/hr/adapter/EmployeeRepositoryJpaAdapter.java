package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	private EmployeeEntityRepository employeeEntityRepository;
	private ModelMapper modelMapper;
	
	public EmployeeRepositoryJpaAdapter(EmployeeEntityRepository employeeEntityRepository, ModelMapper modelMapper) {
		this.employeeEntityRepository = employeeEntityRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity) {
		return employeeEntityRepository.findById(identity.getValue())
				                       .map(emp -> modelMapper.map(emp,Employee.class));
	}

	@Override
	@Transactional
	public Employee insert(Employee employee) {
		var entity = modelMapper.map(employee, EmployeeEntity.class);
		return modelMapper.map(employeeEntityRepository.save(entity),Employee.class);
	}

	@Override
	@Transactional
	public Optional<Employee> remove(TcKimlikNo identity) {
		var entity = employeeEntityRepository.findById(identity.getValue());
		entity.ifPresent(employeeEntityRepository::delete);
		return entity.map( emp -> modelMapper.map(emp, Employee.class));
	}

	@Override
	@Transactional
	public void update(Employee employee) {
		employeeEntityRepository.save(modelMapper.map(employee, EmployeeEntity.class));
	}

}
