package com.example.hr.exercise;

public class Exercise1 {
	public static void main(String[] args) {
		Integer i = Integer.valueOf(42);
		Integer j = 42;
		Integer m = 542;
		Integer n = 542;
		System.out.println("i==j? " + (i == j)); // true
		System.out.println("m==n? " + (m == n)); // false
	}
}
