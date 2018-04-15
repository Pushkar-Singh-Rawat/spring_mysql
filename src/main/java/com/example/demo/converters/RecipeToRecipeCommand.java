package com.example.demo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.demo.commands.RecipeCommand;
import com.example.demo.model.Recipe;

import lombok.Synchronized;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
	private final CategoryToCategoryCommand categoryToCategoryCommand;
	private final NotesToNotesCommand notesConverter;
	private final IngredientToIngredientCommand ingredientConverter;

	public RecipeToRecipeCommand(CategoryToCategoryCommand categoryToCategoryCommand,
			NotesToNotesCommand notesConverter, IngredientToIngredientCommand ingredientConverter) {
		super();
		this.categoryToCategoryCommand = categoryToCategoryCommand;
		this.notesConverter = notesConverter;
		this.ingredientConverter = ingredientConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		// TODO Auto-generated method stub
		if (source == null)
			return null;
		final RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setCookTime(source.getCookTime());
		recipeCommand.setDescription(source.getDescription());
		recipeCommand.setDifficulty(source.getDifficulty());
		recipeCommand.setDirections(source.getDirections());
		recipeCommand.setPrepTime(source.getPrepTime());
		recipeCommand.setRecipeID(source.getRecipeID());
		recipeCommand.setServings(source.getServings());
		recipeCommand.setImage(source.getImage());
		recipeCommand.setUrl(source.getUrl());
		recipeCommand.setNotes(notesConverter.convert(source.getNotes()));
		if (source != null && source.getCategory().size() > 0) {
			source.getCategory().forEach(
					category -> recipeCommand.getCategories().add(categoryToCategoryCommand.convert(category)));
		}
		if (source.getIngredient() != null && source.getIngredient().size() > 0) {
			source.getIngredient()
					.forEach(ingredient -> recipeCommand.getIngredient().add(ingredientConverter.convert(ingredient)));
		}
		return recipeCommand;
	}

}
