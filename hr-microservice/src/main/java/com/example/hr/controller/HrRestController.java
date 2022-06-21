package com.example.hr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.dto.error.RestErrorMessage;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.request.UpdateEmployeeSalaryRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.dto.response.UpdateEmployeeSalaryResponse;
import com.example.hr.service.HrService;
import com.example.validation.TcKimlikNo;

// ✔ Resource-Oriented Rest API vs RPC-style Rest API
@RestController
@RequestScope
@RequestMapping("employees") // resource -> aggregate/entity root -> Employee
@Validated
@CrossOrigin
// Adapter: Http Protocol <--> Java Class
public class HrRestController {
	private HrService hrService;
	
	public HrRestController(HrService hrService) {
		this.hrService = hrService;
	}

	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody @Validated HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}

	@DeleteMapping("{identity}")
	public FireEmployeeResponse fireEmployee(@PathVariable @TcKimlikNo String identity) {
		return hrService.fireEmployee(identity);
		
	}
	
	@PutMapping("{identity}")
	public UpdateEmployeeSalaryResponse updateEmployeeSalary(@PathVariable @TcKimlikNo String identity, @RequestBody @Validated UpdateEmployeeSalaryRequest request) {
		return hrService.updateEmployeeSalary(identity,request);
	}
	
	@GetMapping("{identity}")
	public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable @TcKimlikNo String identity) {
		try{
			return ResponseEntity.ok(hrService.findEmployee(identity));
		}
		catch(EmployeeNotFoundException e) {
			return ResponseEntity.badRequest().body(new EmployeeResponse("failure",e.getMessage()));
		}
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleIllegalArgumentException(IllegalArgumentException e) {
		return new RestErrorMessage(e.getMessage(), 100, "5b0d4d5e-f161-11ec-8ea0-0242ac120002");
	}
}
