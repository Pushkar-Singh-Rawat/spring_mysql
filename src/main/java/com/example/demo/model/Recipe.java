package com.example.demo.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // underlying pa would
														// generate the unique
														// id sequence
	// accordingly
	private Long recipeID;

	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String url;
	@Lob
	private String directions;
	private String description;
	@Lob
	private Byte[] image; // will be stored as a BLOB in db
	@OneToOne(cascade = CascadeType.ALL) // owner entity for Notes. If any state
											// change happens for Recipe then
											// Notes
	// would get that.
	private Notes notes;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe") // as recipe owns
																// Ingredient.
	private Set<Ingredient> ingredient = new HashSet<>(); // this will be mapped
															// by the recipe
															// property of
															// ingredient
	@Enumerated(value = EnumType.STRING) // db will have string values saved
											// into it.
	private Difficulty difficulty;
	@ManyToMany
	@JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_recipeID"), inverseJoinColumns = @JoinColumn(name = "category_catgID")) // join
																																							// colm
																																							// naming
																																							// convention:
																																							// tablename_idname
	// inverseJoinColumns and joincolumns to implement bidirectional join.
	public Set<Category> categories = new HashSet<>();

	public Set<Ingredient> getIngredient() {
		return ingredient;
	}

	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setIngredient(Set<Ingredient> ingredient) {
		this.ingredient = ingredient;
	}

	public Recipe addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		this.ingredient.add(ingredient);
		return this;
	}

	public Long getRecipeID() {
		return recipeID;
	}

	public void setRecipeID(Long recipeID) {
		this.recipeID = recipeID;
	}

	public Integer getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	public Integer getCookTime() {
		return cookTime;
	}

	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	public Integer getServings() {
		return servings;
	}

	public void setServings(Integer servings) {
		this.servings = servings;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public Notes getNotes() {
		return notes;
	}

	public void setNotes(Notes notes) {
		if (notes != null) {
			this.notes = notes;
			notes.setRecipe(this);
		}

	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Set<Category> getCategory() {
		return categories;
	}

	public void setCategory(Set<Category> categories) {
		this.categories = categories;
	}

}
