package com.example.demo.services;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.commands.IngredientCommand;
import com.example.demo.commands.UnitOfMeasurementCommand;
import com.example.demo.converters.IngredientCommandToIngredient;
import com.example.demo.converters.IngredientToIngredientCommand;
import com.example.demo.converters.UnitOfMeasurementToUnitOfMeasurementCommand;
import com.example.demo.converters.UnitOfMeaurementCommandToUnitOfMesurement;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Recipe;
import com.example.demo.respositories.RecipeRepository;
import com.example.demo.respositories.UnitOfMeasurementRepository;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

public class IngredientServiceImplTests {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	@Mock
	RecipeRepository recipeRepository;
	IngredientService ingredientService;
	UnitOfMeasurementRepository unitOfMeasurementRepository;
	public IngredientServiceImplTests() {
		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(
				new UnitOfMeasurementToUnitOfMeasurementCommand());
		this.ingredientCommandToIngredient=new IngredientCommandToIngredient(
				new UnitOfMeaurementCommandToUnitOfMesurement());
	}

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand,unitOfMeasurementRepository,ingredientCommandToIngredient);
	}

	@Test
	public void testFindByRecipeIdAndIngredientId() throws Exception {

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setIngredientID(Long.valueOf(1L));
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setIngredientID(Long.valueOf(2L));
		Ingredient ingredient3 = new Ingredient();
		ingredient3.setIngredientID(Long.valueOf(3L));
		Recipe recipe = new Recipe();
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		recipe.setRecipeID(Long.valueOf(1L));
		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		recipe.addIngredient(ingredient3);
		Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);
		// given
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientID(1L, 2L);

		// then
		assertEquals(Long.valueOf(2L), ingredientCommand.getIngredientID());
		assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeID());
		Mockito.verify(recipeRepository, Mockito.times(1)).findById(Mockito.anyLong());

	}
}
