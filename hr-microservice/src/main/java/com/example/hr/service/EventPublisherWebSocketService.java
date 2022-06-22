package com.example.hr.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.HrEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventPublisherWebSocketService {
	private final MessageClient messageClient;
	private final ObjectMapper objectMapper;
	

	public EventPublisherWebSocketService(MessageClient messageClient, ObjectMapper objectMapper) {
		this.messageClient = messageClient;
		this.objectMapper = objectMapper;
	}


	@EventListener
	public void publishEventToWebSocket(HrEvent event) {
		try {
			var json = objectMapper.writeValueAsString(event);
			messageClient.sendMessage(json);			
		}catch (JsonProcessingException e) {
			System.err.println("Error while converting to json: %s".formatted(e.getMessage()));
		}
	}
}
