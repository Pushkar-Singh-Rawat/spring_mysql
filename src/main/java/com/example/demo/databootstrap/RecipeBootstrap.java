package com.example.demo.databootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.demo.model.Category;
import com.example.demo.model.Difficulty;
import com.example.demo.model.Ingredient;
import com.example.demo.model.Notes;
import com.example.demo.model.Recipe;
import com.example.demo.model.UnitOfMeasurement;
import com.example.demo.respositories.CategoryRepository;
import com.example.demo.respositories.RecipeRepository;
import com.example.demo.respositories.UnitOfMeasurementRepository;

@Component

public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private RecipeRepository recipeRepository;
	private CategoryRepository categoryRepository;
	private UnitOfMeasurementRepository unitOfMeasurementRepository;

	public RecipeBootstrap() {

	}

	@Autowired
	public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
			UnitOfMeasurementRepository unitOfMeasurementRepository) {

		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.unitOfMeasurementRepository = unitOfMeasurementRepository;
	}

	public List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<Recipe>();

		// adding categories
		Optional<Category> indianCategoryOptional = categoryRepository.findByDescription("Indian");
		if (!indianCategoryOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		if (!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
		if (!italianCategoryOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		Optional<Category> fastFoodCategoryOptional = categoryRepository.findByDescription("Fast Food");
		if (!fastFoodCategoryOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}

		// get all the categories added above
		Category indianCategory = indianCategoryOptional.get();
		Category italianCategory = italianCategoryOptional.get();
		Category americanCategory = americanCategoryOptional.get();
		Category fastfoodCategory = fastFoodCategoryOptional.get();

		// adding "each" units of measurement
		Optional<UnitOfMeasurement> eachUOMOptional = unitOfMeasurementRepository.findByDescription("Each");
		if (!eachUOMOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		// adding "Teaspoon" units of measurement
		Optional<UnitOfMeasurement> teaspoonUOMOptional = unitOfMeasurementRepository.findByDescription("Teaspoon");
		if (!teaspoonUOMOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		// adding "Tablespoon" units of measurement
		Optional<UnitOfMeasurement> tablespoonUOMOptional = unitOfMeasurementRepository.findByDescription("Tablespoon");
		if (!tablespoonUOMOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		// adding "Dash" units of measurement
		Optional<UnitOfMeasurement> dashUOMOptional = unitOfMeasurementRepository.findByDescription("Dash");
		if (!dashUOMOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		// adding "Pinch" units of measurement
		Optional<UnitOfMeasurement> pinchUOMOptional = unitOfMeasurementRepository.findByDescription("Pinch");
		if (!pinchUOMOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		// adding "ounce" units of measurement
		Optional<UnitOfMeasurement> ounceUOMOptional = unitOfMeasurementRepository.findByDescription("Ounce");
		if (!ounceUOMOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		Optional<UnitOfMeasurement> cupUOMOptional = unitOfMeasurementRepository.findByDescription("Cup");
		if (!cupUOMOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		// get all the UOM added above
		UnitOfMeasurement eachUOM = eachUOMOptional.get();
		UnitOfMeasurement dashUOM = dashUOMOptional.get();
		UnitOfMeasurement pinchUOM = pinchUOMOptional.get();
		UnitOfMeasurement tablespoonUOM = tablespoonUOMOptional.get();
		UnitOfMeasurement teaspoonUOM = teaspoonUOMOptional.get();
		UnitOfMeasurement ounceUOM = ounceUOMOptional.get();
		UnitOfMeasurement cupUOM = cupUOMOptional.get();

		// recipes

		Recipe bundtCakeRecipe = new Recipe();
		bundtCakeRecipe.setDescription("Lemon Poppy Seed Bundt Cake");
		bundtCakeRecipe.setCookTime(9);
		bundtCakeRecipe.setDifficulty(Difficulty.MIDIUM);
		bundtCakeRecipe.setDirections("1. Prep the pan: Preheat the oven to 350°F with a rack in the middle position."
				+ "Generously spray a 10-cup capacity bundt pan with baking spray. \n"
				+ "2. Mix the dry ingredients: In a large mixing bowl, whisk together"
				+ " the flour, baking powder, baking soda, salt, and poppy seeds.\n"
				+ "3. Make the batter: In the bowl of a stand mixer, beat the butter until creamy."
				+ " Add the sugar and lemon zest, beat for 3 to 4 minutes, or until pale and fluffy."
				+ " Scrape down the sides of the bowl and beat in the eggs one at a time,"
				+ " scraping the bowl after each addition.\r\n" + "\r\n"
				+ "Whisk the lemon juice and milk together in a measuring cup. With the mixer on medium-low,"
				+ " beat in a third of the flour mixture, followed by a third of the milk mixture."
				+ " Continue to add the flour and milk, alternating between the two and scraping"
				+ " down the sides of the bowl as needed.");
		Notes cakeNotes = new Notes();
		cakeNotes.setRecipeNotes("dummy notes");
		//cakeNotes.setRecipe(bundtCakeRecipe);
		
		bundtCakeRecipe.setPrepTime(25);
		bundtCakeRecipe.setNotes(cakeNotes);
		bundtCakeRecipe.getCategory().add(americanCategory);
		bundtCakeRecipe.getIngredient().add(new Ingredient("butter", new BigDecimal(2), tablespoonUOM,bundtCakeRecipe));
		bundtCakeRecipe.getIngredient().add(new Ingredient("lemon", new BigDecimal(2), dashUOM,bundtCakeRecipe));
		bundtCakeRecipe.getIngredient().add(new Ingredient("cocoa", new BigDecimal(2), ounceUOM,bundtCakeRecipe));
		bundtCakeRecipe.setServings(2);
		bundtCakeRecipe.setUrl("https://www.facebook.com");

		recipes.add(bundtCakeRecipe);

		Recipe butterMasala = new Recipe();
		butterMasala.setDescription("Paneer Butter Masala");
		butterMasala.setCookTime(9);
		butterMasala.setPrepTime(20);
		butterMasala.setDifficulty(Difficulty.EASY);
		butterMasala.setDirections("1. Prep the pan: Preheat the oven to 350°F with a rack in the middle position."
				+ "Generously spray a 10-cup capacity bundt pan with baking spray. \n"
				+ "2. Mix the dry ingredients: In a large mixing bowl, whisk together"
				+ " the flour, baking powder, baking soda, salt, and poppy seeds.\n"
				+ "3. Make the batter: In the bowl of a stand mixer, beat the butter until creamy."
				+ " Add the sugar and lemon zest, beat for 3 to 4 minutes, or until pale and fluffy."
				+ " Scrape down the sides of the bowl and beat in the eggs one at a time,"
				+ " scraping the bowl after each addition.\r\n" + "\r\n"
				+ "Whisk the lemon juice and milk together in a measuring cup. With the mixer on medium-low,"
				+ " beat in a third of the flour mixture, followed by a third of the milk mixture."
				+ " Continue to add the flour and milk, alternating between the two and scraping"
				+ " down the sides of the bowl as needed.");
		Notes butterNotes = new Notes();
		butterNotes.setRecipeNotes("paneer butter masala notes");
		//butterNotes.setRecipe(butterMasala); removed
		butterMasala.setNotes(butterNotes);  //added logic to add recipe(any "being" accessed recipe) to the notes object
		butterMasala.getCategory().add(indianCategory);
		butterMasala.getCategory().add(americanCategory);
		/*butterMasala.getIngredient().add(new Ingredient("butter", new BigDecimal(2), tablespoonUOM,butterMasala));*/
		//below addIngredient makes code cleaner by removing method chainings.
		butterMasala.addIngredient(new Ingredient("butter", new BigDecimal(2), tablespoonUOM));
		butterMasala.getIngredient().add(new Ingredient("paneer", new BigDecimal(2), dashUOM,butterMasala));
		butterMasala.getIngredient().add(new Ingredient("jeera", new BigDecimal(2), ounceUOM,butterMasala));
		butterMasala.setServings(4);
		butterMasala.setUrl("https://www.google.com");
		

		recipes.add(butterMasala);
		return recipes;
	}

	@Override
	@Transactional //now every ops happens in the same txn session
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		recipeRepository.saveAll(getRecipes());
	}

}
