package com.example.demo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.demo.commands.NotesCommand;
import com.example.demo.commands.RecipeCommand;
import com.example.demo.model.Recipe;

import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
	private final CategoryCommandToCategory categoryConverter;
	private final NotesCommandToNotes notesConverter;
	private final IngredientCommandToIngredient ingredientConverter;

	public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter, NotesCommandToNotes notesConverter,
			IngredientCommandToIngredient ingredientConverter) {
		this.categoryConverter = categoryConverter;
		this.notesConverter = notesConverter;
		this.ingredientConverter = ingredientConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		final Recipe recipe = new Recipe();
		if (source == null)
			return null;
		recipe.setCookTime(source.getCookTime());
		recipe.setDescription(source.getDescription());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setDirections(source.getDirections());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setRecipeID(source.getRecipeID());
		recipe.setServings(source.getServings());
		recipe.setUrl(source.getUrl());
		recipe.setNotes(notesConverter.convert(source.getNotes()));
		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach(category -> recipe.getCategory().add(categoryConverter.convert(category)));
		}
		if (source.getIngredient() != null && source.getIngredient().size() > 0) {
			source.getIngredient()
					.forEach(ingredient -> recipe.getIngredient().add(ingredientConverter.convert(ingredient)));
		}
		return recipe;

	}

}
