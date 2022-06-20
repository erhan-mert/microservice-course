package com.example.hr.domain;

import java.util.Base64;
import java.util.Objects;

@ValueObject(factoryMethod = "valuesOf")
public final class Photo {
	private final byte[] values;

	private Photo(byte[] values) {
		this.values = values;
	}

	public static Photo valuesOf(byte[] values) {
		Objects.requireNonNull(values);
		return new Photo(values);
	}

	public static Photo valuesOf(String values) {
		Objects.requireNonNull(values);
		return valuesOf(Base64.getDecoder().decode(values));
	}

	public byte[] getValues() {
		return values;
	}

	public String getBase64Values() {
		return Base64.getEncoder().encodeToString(values);
	}
}
