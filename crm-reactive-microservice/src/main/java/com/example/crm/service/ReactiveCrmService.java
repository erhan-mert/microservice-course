package com.example.crm.service;

import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerDocumentReactiveRepository;

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

}
