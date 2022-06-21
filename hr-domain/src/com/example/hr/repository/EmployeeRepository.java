package com.example.hr.repository;

import java.util.Optional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.Port;
import com.example.hr.domain.Side;
import com.example.hr.domain.TcKimlikNo;

@Port(side=Side.DRIVEN)
public interface EmployeeRepository {

	Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity);

	Employee insert(Employee employee);

	Optional<Employee> remove(TcKimlikNo identity);

}
