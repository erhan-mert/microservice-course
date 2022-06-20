package com.example.hr.domain;

import java.util.Objects;

@ValueObject(factoryMethod = "of")
public final class BirthYear {
	private final int value;

	private BirthYear(int value) {
		this.value = value;
	}

	public static BirthYear of(int value) {
		if (value > 2006)
			throw new IllegalArgumentException("Birth year must be before 2006.");
		return new BirthYear(value);
	}

	public int getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BirthYear other = (BirthYear) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return "BirthYear [value=" + value + "]";
	}

}
