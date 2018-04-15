package com.example.demo.services;




import com.example.demo.commands.IngredientCommand;
import com.example.demo.model.Ingredient;

public interface IngredientService{


	IngredientCommand findByRecipeIdAndIngredientID(Long recipeID, Long ingredientID);

	IngredientCommand saveIngredientCommand(IngredientCommand command);

	void deleteById(Long recipeid, Long ingredientid);
	
}
