package com.example.demo.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Recipe;
import com.example.demo.respositories.RecipeRepository;

public class RecipeServiceImplTests {
	// test for service layer business logic
	// mockito will mock the respositoryclass
	// a pure junit test that leverages mockito mocks
	RecipeService recipeService;
	@Mock
	RecipeRepository recipeRepository;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this); // initiates the mock. mockito will return recipeRepository mock

		recipeService = new RecipeServiceImpl(recipeRepository,null,null);
	}

	@Test
	public void getRecipeByIdTest() throws Exception {

		Recipe recipe=new Recipe();
		recipe.setRecipeID(1L);
		Optional<Recipe> recipeOptional=Optional.of(recipe);
		when(recipeRepository.findById(1L)).thenReturn(recipeOptional);
		Recipe recipeReturned=recipeService.findById(1L);
		assertNotNull("Null recipe recieved",recipeReturned);
		verify(recipeRepository,times(1)).findById(Mockito.anyLong());
		verify(recipeRepository,never()).findAll();
	}
	
	@Test(expected=NotFoundException.class)
	public void getRecipeByIdTestNotFound() throws Exception{
		Optional<Recipe> recipeOptional=Optional.empty();
		when(recipeRepository.findById(1L)).thenReturn(recipeOptional);
		Recipe returnedRecipe=recipeService.findById(1L);
	}
	

	@Test
	public void getRecipe() throws Exception {

		Recipe recipe = new Recipe();
		HashSet<Recipe> recipesSet = new HashSet<>();
		recipesSet.add(recipe);
		when(recipeService.getRecipe()).thenReturn(recipesSet);

		Set<Recipe> recipes = recipeService.getRecipe();
		assertEquals(recipes.size(), 1);
		verify(recipeRepository, times(1)).findAll(); // findAll should be called only once as expected
	}
	
	
	
	
	
	@Test
	public void testDeleteRecipe() throws Exception{
		//given
		Long id=Long.valueOf(2L);
		//when
		recipeService.deleteById(id);

		//then
		//below step verifies recipeRepository is called single time and DeleteByID() is invoked with any long val
		verify(recipeRepository,times(1)).deleteById(Mockito.anyLong());
	}
}
