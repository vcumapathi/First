package org.fst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Recipe")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="recipeId")
	private Integer recipeId;
	
	@Column(name="recipeName")
	private String recipeName;
	
	@Column(name="ingredientOne")
	private String ingredientOne;
	
	@Column(name="ingredientTwo")
	private String ingredientTwo;
	
	@Column(name="ingredientThree")
	private String ingredientThree;
	
	@Column(name="ingredientFour")
	private String ingredientFour;
	
	@Transient
	private String status;

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getIngredientOne() {
		return ingredientOne;
	}

	public void setIngredientOne(String ingredientOne) {
		this.ingredientOne = ingredientOne;
	}

	public String getIngredientTwo() {
		return ingredientTwo;
	}

	public void setIngredientTwo(String ingredientTwo) {
		this.ingredientTwo = ingredientTwo;
	}

	public String getIngredientThree() {
		return ingredientThree;
	}

	public void setIngredientThree(String ingredientThree) {
		this.ingredientThree = ingredientThree;
	}

	public String getIngredientFour() {
		return ingredientFour;
	}

	public void setIngredientFour(String ingredientFour) {
		this.ingredientFour = ingredientFour;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}

