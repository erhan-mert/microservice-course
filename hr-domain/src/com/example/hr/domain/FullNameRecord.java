package com.example.hr.domain;

import java.util.Objects;

public record FullNameRecord(String firstName, String lastName) {
	public static FullNameRecord of(String firstName, String lastName) {
		Objects.requireNonNull(firstName, "First name must have a value.");
		Objects.requireNonNull(lastName, "Last name must have a value.");
		return new FullNameRecord(firstName, lastName);
	}
}

class Exercise2 {
	void fun() {
		var fullName = FullNameRecord.of("jack", "bauer");
		System.out.println(fullName.firstName());
		System.out.println(fullName.lastName());
	}
}