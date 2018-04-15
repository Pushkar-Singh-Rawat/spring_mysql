package com.example.demo.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.commands.IngredientCommand;
import com.example.demo.converters.IngredientCommandToIngredient;
import com.example.demo.converters.IngredientToIngredientCommand;
import com.example.demo.converters.RecipeToRecipeCommand;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Recipe;
import com.example.demo.respositories.RecipeRepository;
import com.example.demo.respositories.UnitOfMeasurementRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	RecipeRepository recipeRepository;
	IngredientToIngredientCommand ingredientToIngredientCommand;
	IngredientCommandToIngredient ingredientCommandToIngredient;
	/* RecipeToRecipeCommand recipeToRecipeCommand; */
	UnitOfMeasurementRepository unitOfMeasurementRepository;

	@Autowired // just to mark that via constructor we are performing DI
	public IngredientServiceImpl(RecipeRepository recipeRepository,
			IngredientToIngredientCommand ingredientToIngredientCommand,
			UnitOfMeasurementRepository unitOfMeasurementRepository,
			IngredientCommandToIngredient ingredientCommandToIngredient/*
																		 * ,RecipeToRecipeCommand
																		 * recipeToRecipeCommand
																		 */) {

		this.recipeRepository = recipeRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.unitOfMeasurementRepository = unitOfMeasurementRepository;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		/* this.recipeToRecipeCommand=recipeToRecipeCommand; */

	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientID(Long recipeid, Long ingredientid) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeid);

		if (!recipeOptional.isPresent()) {
			log.error("recipe corresponding to" + recipeid + " is not present");
		}
		Recipe recipe = recipeOptional.get();
		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredient().stream()
				.filter(ingredient -> ingredient.getIngredientID().equals(ingredientid))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

		if (!ingredientCommandOptional.isPresent()) {
			log.error("ingredient with id " + ingredientid + " is not present");
		}

		/*
		 * Optional<Recipe>
		 * recipe=recipeRepository.findById(recipeid);
		 * Set<Ingredient> ingredientC=recipe.get().getIngredient();
		 * for(Ingredient ingred:ingredient){
		 * if(ingred.getIngredientID().equals(Long.valueOf(ingredientid))){
		 * return ingredientToIngredientCommand.convert(ingred)); } }
		 */
		System.out.println("-----------------------" + ingredientCommandOptional.get().getRecipeID());
		// ingredientCommandOptional.get().setRecipe(recipeToRecipeCommand.convert(recipe));
        ingredientCommandOptional.get().setRecipeID(recipeid);
		return ingredientCommandOptional.get();
	}

	@Transactional
	@Override
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		// TODO Auto-generated method stub

		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeID());
		if (!recipeOptional.isPresent()) {
			log.error("recipe with id: " + command.getRecipeID() + " is not present");
			return new IngredientCommand();
		}

		else {
			Recipe recipe = recipeOptional.get();

			Optional<Ingredient> ingredientOptional = recipe.getIngredient().stream()
					.filter(ingredient -> ingredient.getIngredientID().equals(command.getIngredientID())).findFirst();

			if (ingredientOptional.isPresent()) {
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setDescription(command.getDescription());
				ingredientFound.setAmount(command.getAmount());
				ingredientFound.setUnitOfMeasurement(
						unitOfMeasurementRepository.findById(command.getUnitOfMeasurement().getUomID())
								.orElseThrow(() -> new RuntimeException("UOM NOT FOUND!")));
			} else {
				// for adding new ingredient.
				Ingredient newIngredient = ingredientCommandToIngredient.convert(command);
				newIngredient.setRecipe(recipe);
				recipe.addIngredient(newIngredient);
			}

			Recipe savedRecipe = recipeRepository.save(recipe);
			Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredient().stream()
					.filter(ingredient -> ingredient.getIngredientID().equals(command.getIngredientID())).findFirst();

			if (!savedIngredientOptional.isPresent()) {
				savedIngredientOptional = savedRecipe.getIngredient().stream()
						.filter(ingredient -> ingredient.getUnitOfMeasurement().getUomID()
								.equals(command.getUnitOfMeasurement().getUomID()))
						.filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
						.filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))

						.findFirst();

			}
			return ingredientToIngredientCommand.convert(savedIngredientOptional.get());

		}

	}

	@Override
	public void deleteById(Long recipeid, Long ingredientid) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeid);
		if (recipeOptional.isPresent()) {
			Recipe recipe = recipeOptional.get();
			Optional<Ingredient> ingredientOptional = recipe.getIngredient().stream()
					.filter(ingredient -> ingredient.getIngredientID().equals(ingredientid)).findFirst();
			if (ingredientOptional.isPresent()) {
				
				ingredientOptional.get().setRecipe(null);
				recipe.getIngredient().remove(ingredientOptional.get());
				recipeRepository.save(recipe);
			}
			else{
				log.debug("ingredient with id "+ingredientid +"is not present");
			}
		}
		else{
			log.debug("recipe with id "+recipeid +"is not present");
		}

	}

}
