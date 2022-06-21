package com.example.hr.infrastructure;

import com.example.hr.domain.Port;
import com.example.hr.domain.Side;

@Port(side = Side.DRIVEN)
public interface EventPublisher<E> {
	void publishEvent(E event);
}
