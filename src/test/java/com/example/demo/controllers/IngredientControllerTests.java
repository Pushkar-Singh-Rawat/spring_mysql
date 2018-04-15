package com.example.demo.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ModelResultMatchers;

import com.example.demo.commands.IngredientCommand;
import com.example.demo.services.IngredientService;
import com.example.demo.services.RecipeService;
import com.example.demo.services.UnitOfMeasurementService;

public class IngredientControllerTests {
	MockMvc mockMVC;
	IngredientController ingredientController;
	@Mock
	RecipeService recipeService;
	@Mock
	IngredientService ingredientService;
	@Mock
	UnitOfMeasurementService unitOfMeasurementService;


	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		ingredientController = new IngredientController(recipeService,ingredientService,unitOfMeasurementService);
		mockMVC = MockMvcBuilders.standaloneSetup(ingredientController).build();
	}

	@Test
	public void testShowIngredient() throws Exception {

		//given
		IngredientCommand ingredientCommand=new IngredientCommand();
		
		//when
		Mockito.when(ingredientService.findByRecipeIdAndIngredientID(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ingredientCommand);
		//then
		mockMVC.perform(get("/recipe/1/ingredient/2/show/")).andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/show"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"));

	}
}
