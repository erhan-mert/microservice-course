package com.example.hr.domain.event;

import com.example.hr.domain.TcKimlikNo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HrEvent {
	private final TcKimlikNo identity;
	
}
