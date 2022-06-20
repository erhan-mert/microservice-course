package com.example.hr.exercise;

import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;

@SuppressWarnings("unused")
public class Exercise2 {
	public static void main(String[] args) {
		for (var jobStyle : JobStyle.values()) {
			System.out.println("%s\t%d".formatted(jobStyle.name(), jobStyle.ordinal()));
		}
		var jobStyle = JobStyle.valueOf("FULL_TIME");
		// new JobStyle("");
		// Enum is a Value Object!
		for (var currency : FiatCurrency.values()) {
			System.out.println("%s\t%s".formatted(currency.name(), currency.getSymbol()));
		}

	}
}
