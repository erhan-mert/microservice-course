package com.example.sc.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.example.sc.dto.HrEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HrEventKafkaListener {
	private ObjectMapper mapper;
	
	public HrEventKafkaListener(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@KafkaListener(topics = "hr-events", groupId = "security-card")
	public void listenHrEvents(String event) throws Exception {
		System.err.println(Thread.currentThread().getName().concat(" is handling hr event..."));
		var hrEvent= mapper.readValue(event, HrEvent.class);
		System.out.println(Thread.currentThread().getName()+": "+"New event has arrived: %s".formatted(hrEvent));
		var restTemplate = new RestTemplate();
		String kimlik = hrEvent.getIdentity().getValue();
		// REST ON HTTP -- Sync.
		var employee = restTemplate.getForObject("http://localhost:7100/hr/api/v1/employees/%s".formatted(kimlik), String.class);
		System.out.println(Thread.currentThread().getName()+": "+employee);
		// REST ON HTTP -- Async.
		var asyncRestTemplate = new AsyncRestTemplate();
		asyncRestTemplate.getForEntity("http://localhost:7100/hr/api/v1/employees/%s".formatted(kimlik), String.class)
	                     .addCallback(er -> System.out.println(Thread.currentThread().getName()+": "+er.getBody()), err -> System.err.println(err.getMessage())); 
	                	 //.addCallback(System.out::println, System.err::println); 
	}
}
