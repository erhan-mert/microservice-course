package com.example.hr.domain;

@ValueObject(factoryMethod = "valueOf")
public enum FiatCurrency {
	EUR("€"), USD("$"), TL("\u20ba");

	private final String symbol;

	private FiatCurrency(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

}
