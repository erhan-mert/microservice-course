package com.example.hr.adapter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.hr.domain.event.HrEvent;
import com.example.hr.infrastructure.EventPublisher;

@Service
public class EventPublisherSpringAdapter implements EventPublisher<HrEvent> {
	private ApplicationEventPublisher eventPublisher;
	
	public EventPublisherSpringAdapter(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void publishEvent(HrEvent event) {
		eventPublisher.publishEvent(event);
	}

}
