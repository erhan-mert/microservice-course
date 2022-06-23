package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerDocumentReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveCrmService {

	private final CustomerDocumentReactiveRepository documentRepository;
	
	public ReactiveCrmService(CustomerDocumentReactiveRepository documentRepository) {
		this.documentRepository = documentRepository;
	}

	public Mono<CustomerDocument> findById(String customerId) {
		return documentRepository.findById(customerId);
	}

	public Flux<CustomerDocument> findAllByPage(int pageNo, int pageSize) {
		return documentRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	public Mono<CustomerDocument> acquireCustomer(CustomerDocument document) {
		return documentRepository.insert(document);
	}

	public Mono<CustomerDocument> updateCustomer(CustomerDocument document) {
		return documentRepository.save(document);
	}

	public Mono<CustomerDocument> releaseCustomerById(String customerId) {
		return documentRepository.findById(customerId)
             .doOnSuccess(
            	customer ->	documentRepository.delete(customer).subscribe(System.out::println)
              );
	}

}
