package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
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
	
	@GetMapping("{identity}")
	public EmployeeResponse getEmployee(@PathVariable @TcKimlikNo String identity) {
		return hrService.findEmployee(identity);		
	}

	
}
