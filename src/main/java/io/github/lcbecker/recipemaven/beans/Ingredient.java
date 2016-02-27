package io.github.lcbecker.recipemaven.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingredient {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("amount")
	private double amount;
	
	@JsonProperty("unit")
	private String unit;
	
	@JsonProperty("description")
	private String description;
	
	public Ingredient() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
