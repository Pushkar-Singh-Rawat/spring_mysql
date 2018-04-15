package com.example.demo.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@EqualsAndHashCode(exclude={"recipe"})
public class Ingredient {

	public Ingredient() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingredientID;
	private String description;
	private BigDecimal amount;
	@ManyToOne // no cascading is needed. As per data model design.
	private Recipe recipe; // to which ingredient belongs

	@OneToOne(fetch = FetchType.EAGER) // fetched everytime from db (by default OneToOne is eager)
	private UnitOfMeasurement unitOfMeasurement;

	public Ingredient(String description, BigDecimal amount, UnitOfMeasurement unitOfMeasurement,Recipe recipe) {
		this.description = description;
		this.amount = amount;
		this.unitOfMeasurement = unitOfMeasurement;
		this.recipe=recipe;
	}

	public Ingredient(String description, BigDecimal amount, UnitOfMeasurement unitOfMeasurement) {
		this.description = description;
		this.amount = amount;
		this.unitOfMeasurement = unitOfMeasurement;
	}

/*	public Long getIngredientID() {
		return ingredientID;
	}

	public void setIngredientID(Long ingredientID) {
		this.ingredientID = ingredientID;
	}

	public UnitOfMeasurement getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
*/
}
