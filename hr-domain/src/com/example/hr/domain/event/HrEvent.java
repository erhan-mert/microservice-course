package com.example.hr.domain.event;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.example.hr.domain.TcKimlikNo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HrEvent {
	private TcKimlikNo identity;
	private String eventId = UUID.randomUUID().toString();
	private long sequence = ZonedDateTime.now().toEpochSecond();
	private HrEventType eventType;
	public HrEvent(TcKimlikNo identity, HrEventType eventType) {
		this.identity = identity;
		this.eventType = eventType;
	}
	
}
