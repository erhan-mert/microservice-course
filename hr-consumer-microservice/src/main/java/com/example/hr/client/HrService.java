package com.example.hr.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.request.UpdateEmployeeSalaryRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.dto.response.UpdateEmployeeSalaryResponse;

@FeignClient(name="hr")
public interface HrService {
	@PostMapping("/hr/api/v1/employees")
	public HireEmployeeResponse hireEmployee(@RequestBody HireEmployeeRequest request);

	@DeleteMapping("/hr/api/v1/employees/{identity}")
	public FireEmployeeResponse fireEmployee(@PathVariable String identity);

	@PutMapping("/hr/api/v1/employees/{identity}")
	public UpdateEmployeeSalaryResponse updateEmployeeSalary(@PathVariable String identity,
			@RequestBody UpdateEmployeeSalaryRequest request);

	@GetMapping("/hr/api/v1/employees/{identity}")
	public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable String identity);

}
