package com.example.demo.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.example.demo.controllers.HomeController;
import com.example.demo.model.Recipe;
import com.example.demo.services.RecipeService;

public class HomeControllerTests {

	@Mock
	RecipeService recipeService;
	HomeController homeController;
	@Mock
	Model model; // mocked Model interface

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		homeController = new HomeController(recipeService);

	}
	@Test
	public void testMockMvc() throws Exception{
		MockMvc mockMVC=MockMvcBuilders.standaloneSetup(homeController).build();
		mockMVC.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("homePage"));
	}

	@Test
	public void getHome() {
		String homePageNameActual = homeController.getHome(model);
		String homePageNameExpected = "homePage";
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(new Recipe());
		Recipe recipe = new Recipe();
		recipe.setRecipeID(1L);
		recipes.add(recipe);

		when(recipeService.getRecipe()).thenReturn(recipes);
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		assertEquals(homePageNameExpected, homePageNameActual);

		verify(recipeService, times(1)).getRecipe();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		Set<Recipe> recipeSetValue = argumentCaptor.getValue();
	//	assertEquals(2, recipeSetValue);
		/*
		 * verify(recipeService,times(0)).getRecipe();
		 * verify(recipeService,times(2)).getRecipe();
		 */

	}
}
