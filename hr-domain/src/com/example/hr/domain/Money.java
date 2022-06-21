package com.example.hr.domain;

import java.util.Objects;

@ValueObject(factoryMethod = "of")
public final class Money {
	private final double value;
	private final FiatCurrency currency;

	private Money(double value, FiatCurrency currency) {
		this.value = value;
		this.currency = currency;
	}

	private Money(double value) {
		this(value, FiatCurrency.TL);
	}

	public double getValue() {
		return value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public static Money of(double value, FiatCurrency currency) {
		if (value <= 0.)
			throw new IllegalArgumentException("Value cannıt be negative.");
		if (Objects.isNull(currency))
			throw new IllegalArgumentException("Currency cannot be null.");
		return new Money(value, currency);
	}

	public static Money of(double value) {
		return Money.of(value, FiatCurrency.TL);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return currency == other.currency && Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "Money [value=" + value + ", currency=" + currency + "]";
	}

	public Money multiply(double rate) {
		return Money.of((1.0 +rate)*value, this.currency);
	}

}
