package com.example.hr.domain.event;

import com.example.hr.domain.DomainEvent;
import com.example.hr.domain.TcKimlikNo;

@DomainEvent
public class EmployeeFiredEvent extends HrEvent {

	public EmployeeFiredEvent(TcKimlikNo identity) {
		super(identity);
	}

}
