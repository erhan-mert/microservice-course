package com.example.crm.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.CustomerDocument;
import com.example.crm.service.ReactiveCrmService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@CrossOrigin
@Validated
public class CrmRestController {

	private final ReactiveCrmService crmService;
	
	public CrmRestController(ReactiveCrmService crmService) {
		this.crmService = crmService;
	}

	@GetMapping("{customerId}")
	public Mono<CustomerDocument> getCustomerById(@PathVariable String customerId) {
		return crmService.findById(customerId);
	}

	@GetMapping(params = {"pageNo","pageSize"})
	public Flux<CustomerDocument> getCustomerByPage(@RequestParam int pageNo, @RequestParam int pageSize) {
		return crmService.findAllByPage(pageNo,pageSize);
	}
	
	@PostMapping
	public Mono<CustomerDocument> acquireCustomer(@RequestBody CustomerDocument document){
		return crmService.acquireCustomer(document);
	}
	
	@PutMapping
	public Mono<CustomerDocument> updateCustomer(@RequestBody CustomerDocument document){
		return crmService.updateCustomer(document);
	}
	
	@DeleteMapping("{customerId}")
	public Mono<CustomerDocument> releaseCustomerById(@PathVariable String customerId) {
		return crmService.releaseCustomerById(customerId);
	}
	
}
