package com.example.sc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//{"identity":{"value":"84166588398"}}
@Getter
@Setter
@ToString
public class HrEvent {
	private Identity identity;
	private String eventId;
	private long sequence;
	private String eventType;
}
