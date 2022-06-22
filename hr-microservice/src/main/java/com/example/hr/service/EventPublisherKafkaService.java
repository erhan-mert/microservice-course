package com.example.hr.service;

import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.HrEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventPublisherKafkaService  {
	private KafkaTemplate<String,String> kafkaTemplate;
	private ObjectMapper objectMapper;
	

	public EventPublisherKafkaService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}

	@EventListener
	public void publishEventToKafkaTopic(HrEvent event) {
		try {
			var json = objectMapper.writeValueAsString(event);
			kafkaTemplate.send("hr-events", json);			
		}catch (JsonProcessingException e) {
			System.err.println("Error while converting to json: %s".formatted(e.getMessage()));
		}
	}

}
