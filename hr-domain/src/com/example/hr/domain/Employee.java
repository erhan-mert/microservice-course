package com.example.hr.domain;

import java.util.Objects;

// Domain -> i) Entity ✔ ii) Aggregate ✔ iii) Value Object ✔
// Entity -> i) identity: tcKimlikNo ii) Mutable

// DDD: Bounded-Context, Ubiquitous Language
// Domain Model      ->       Solution Model
// Analysis			 ->       Design	
// Sub-domain Model  -> Modeling/Abstraction -> Domain Model
// Bounded-Context -> Ubiq. Lang. -> Domain Expert
@EntityClass(identity = "tcKimlikNo")
@Aggregate // Aggregate ==> Entity Root
// Lombok
public class Employee {
	private final TcKimlikNo tcKimlikNo;
	private FullName fullName;
	private Iban iban;
	private Money salary;
	private final BirthYear birthYear;
	private Photo photo;
	private JobStyle jobStyle;
	private Department department;

	public Employee(TcKimlikNo tcKimlikNo, BirthYear birthYear) {
		this.tcKimlikNo = tcKimlikNo;
		this.birthYear = birthYear;
	}

	public Employee(Builder builder) {
		this.tcKimlikNo = builder.tcKimlikNo;
		this.fullName = builder.fullName;
		this.iban = builder.iban;
		this.salary = builder.salary;
		this.birthYear = builder.birthYear;
		this.photo = builder.photo;
		this.jobStyle = builder.jobStyle;
		this.department = builder.department;
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public Iban getIban() {
		return iban;
	}

	public void setIban(Iban iban) {
		this.iban = iban;
	}

	public Money getSalary() {
		return salary;
	}

	public void setSalary(Money salary) {
		this.salary = salary;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public JobStyle getJobStyle() {
		return jobStyle;
	}

	public void setJobStyle(JobStyle jobStyle) {
		this.jobStyle = jobStyle;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public TcKimlikNo getTcKimlikNo() {
		return tcKimlikNo;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public static class Builder {
		private TcKimlikNo tcKimlikNo;
		private FullName fullName;
		private Iban iban;
		private Money salary;
		private BirthYear birthYear;
		private Photo photo;
		private JobStyle jobStyle;
		private Department department;

		public Builder tcKimlikNo(String value) {
			this.tcKimlikNo = TcKimlikNo.valueOf(value);
			return this;
		}

		public Builder fullName(String firstName, String lastName) {
			this.fullName = FullName.of(firstName, lastName);
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.valueOf(value);
			return this;
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Money.of(value, currency);
			return this;
		}

		public Builder salary(double value) {
			return salary(value, FiatCurrency.TL);
		}

		public Builder birthYear(int value) {
			this.birthYear = BirthYear.of(value);
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = Photo.valuesOf(values);
			return this;
		}

		public Builder photo(String base64Value) {
			this.photo = Photo.valuesOf(base64Value);
			return this;
		}

		public Builder jobStyle(String value) {
			this.jobStyle = JobStyle.valueOf(value);
			return this;
		}

		public Builder department(String value) {
			this.department = Department.valueOf(value);
			return this;
		}

		public Employee build() {
			// validation
			// business rule
			if (department == Department.IT && salary.getValue() < 15_000) {
				throw new IllegalArgumentException("IT Department's min salary is 15,000.");
			}
			// invariants
			// constraint
			if (jobStyle == JobStyle.FULL_TIME && salary.getValue() < 4_500) {
				throw new IllegalArgumentException("min salary is less than 4,500.");
			}
			return new Employee(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(tcKimlikNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(tcKimlikNo, other.tcKimlikNo);
	}

	@Override
	public String toString() {
		return "Employee [tcKimlikNo=" + tcKimlikNo + ", fullName=" + fullName + ", iban=" + iban + ", salary=" + salary
				+ ", birthYear=" + birthYear + ", jobStyle=" + jobStyle + ", department=" + department + "]";
	}

	public void increaseSalary(double rate) {
		this.salary = salary.multiply(rate);
		
	}

}