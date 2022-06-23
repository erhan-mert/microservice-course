package com.example.hr.domain.event;

import com.example.hr.domain.DomainEvent;
import com.example.hr.domain.TcKimlikNo;

@DomainEvent
public class EmployeeHiredEvent extends HrEvent {

	public EmployeeHiredEvent(TcKimlikNo identity) {
		super(identity, HrEventType.EMPLOYEE_HIRED);
	}
	
}
