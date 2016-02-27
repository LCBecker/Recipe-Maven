package io.github.lcbecker.recipemaven.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Recipe {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("servings")
	private int servings;
	
	@JsonProperty("ingredients")
	private Ingredient[] ingredients;
	
	public Recipe() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public Ingredient[] getIngredients() {
		return ingredients;
	}

	public void setIngredients(Ingredient[] ingredients) {
		this.ingredients = ingredients;
	}
	
	

}
