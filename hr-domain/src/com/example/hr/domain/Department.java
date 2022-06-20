package com.example.hr.domain;

@ValueObject(factoryMethod = "valueOf")
public enum Department {
	HR, SALES, FINANCE, IT
}
