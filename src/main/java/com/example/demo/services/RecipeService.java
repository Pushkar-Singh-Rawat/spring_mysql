package com.example.demo.services;

import java.util.Set;

import com.example.demo.commands.RecipeCommand;
import com.example.demo.model.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipe();

	Recipe findById(Long l);

	RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

	RecipeCommand findCommandById(Long recipeCommandID);

	void deleteById(Long id);
}
