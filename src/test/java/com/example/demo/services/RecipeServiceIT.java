package com.example.demo.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.commands.RecipeCommand;
import com.example.demo.converters.RecipeCommandToRecipe;
import com.example.demo.converters.RecipeToRecipeCommand;
import com.example.demo.model.Recipe;
import com.example.demo.respositories.RecipeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {
	public static final String DESCRIPTION = "saved recipe";
	@Autowired
	RecipeRepository recipeRepository;
	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;
	@Autowired
	RecipeCommandToRecipe recipeCommandToRecipe;
	@Autowired
	RecipeService recipeService;

	@Transactional
	@Test
	public void testSaveRecipeCommand() throws Exception {
		// given
		Iterable<Recipe> recipes = recipeRepository.findAll();
		Recipe testRecipe = recipes.iterator().next();
		RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

		// when
		testRecipeCommand.setDescription(DESCRIPTION);
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

		// then
		assertEquals(DESCRIPTION, savedRecipeCommand.getDescription());
		assertEquals(testRecipe.getRecipeID(),savedRecipeCommand.getRecipeID());
		assertEquals(testRecipe.getCategory().size(), savedRecipeCommand.getCategories().size());
		assertEquals(testRecipe.getIngredient().size(), savedRecipeCommand.getIngredient().size());
	}
}
