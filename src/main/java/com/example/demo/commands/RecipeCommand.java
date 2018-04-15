package com.example.demo.commands;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.URL;
import org.springframework.stereotype.Component;

import com.example.demo.model.Category;
import com.example.demo.model.Difficulty;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Notes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {
	private Long recipeID;
	@Min(1)
	@Max(999)
	private Integer prepTime;
	@Min(1)
	@Max(999)
	private Integer cookTime;
	@Min(1)
	@Max(100)
	private Integer servings;
	@URL
	private String url;
	private Byte[] image;
	@NotBlank
	private String directions;
	@NotBlank
	@Size(min=1,max=255)
	private String description;

	private NotesCommand notes;

	private Set<IngredientCommand> ingredient = new HashSet<>();

	private Difficulty difficulty;

	public Set<CategoryCommand> categories = new HashSet<>();
}
