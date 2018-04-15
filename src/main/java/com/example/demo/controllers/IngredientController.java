package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.commands.IngredientCommand;
import com.example.demo.commands.RecipeCommand;
import com.example.demo.commands.UnitOfMeasurementCommand;
import com.example.demo.services.IngredientService;
import com.example.demo.services.RecipeService;
import com.example.demo.services.UnitOfMeasurementService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IngredientController {

	private RecipeService recipeService;
	private IngredientService ingredientService;
	private UnitOfMeasurementService unitOfMeasurementService;

	@Autowired // just to indicate that springcontext injects RecipeServiceImpl
	// object here
	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitOfMeasurementService unitOfMeasurementService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasurementService = unitOfMeasurementService;
	}

	@GetMapping("/recipe/{id}/ingredients") // setting up MVC.
	public String ListIngredients(Model model, @PathVariable String id) {
		model.addAttribute("recipe", recipeService.findById(new Long(id)));
		return "recipe/ingredient/list";
	}

	@GetMapping
	@RequestMapping("/recipe/{recipeid}/ingredient/{ingredientid}/show") // setting
	// up
	// MVC.
	public String showById(Model model, @PathVariable String recipeid, @PathVariable String ingredientid) {

		model.addAttribute("ingredient",
				ingredientService.findByRecipeIdAndIngredientID(Long.valueOf(recipeid), Long.valueOf(ingredientid)));
		return "recipe/ingredient/show";
	}

	@GetMapping("/recipe/{recipeid}/ingredient/{ingredientid}/update") // setting
	// up
	// MVC.
	public String updateRecipeIngredients(@PathVariable String recipeid, @PathVariable String ingredientid,
			Model model) {

		model.addAttribute("ingredient",
				ingredientService.findByRecipeIdAndIngredientID(Long.valueOf(recipeid), Long.valueOf(ingredientid)));
		model.addAttribute("uomList", unitOfMeasurementService.listAllUoms());

		return "recipe/ingredient/ingredientform";
	}

	@GetMapping("/recipe/{recipeid}/ingredient/{ingredientid}/delete") // setting
	// up
	// MVC.
	public String deleteRecipeIngredient(@PathVariable String recipeid, @PathVariable String ingredientid) {

		ingredientService.deleteById(Long.valueOf(recipeid), Long.valueOf(ingredientid));
		return "redirect:/recipe/" + recipeid + "/ingredients";
	}

	@GetMapping("/recipe/{recipeid}/ingredient/new") // setting
	// up
	// MVC.
	public String newRecipe(@PathVariable String recipeid, Model model) {
		RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeid));

		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeID(Long.valueOf(recipeid));
		ingredientCommand.setUnitOfMeasurement(new UnitOfMeasurementCommand());

		model.addAttribute("ingredient", ingredientCommand);
		model.addAttribute("uomList", unitOfMeasurementService.listAllUoms());
		return "recipe/ingredient/ingredientform";
	}

	@PostMapping("/recipe/{recipeid}/ingredient")
	public String saveOrUpdate(@ModelAttribute IngredientCommand command) { //uom and recipeid=null
		System.out.println("++++++++++++++++++++++++++++++" + command.getRecipeID());
		IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(command);

		log.debug("saved recipe id is" + savedIngredientCommand.getRecipeID());
		log.debug("saved ingredient id is" + savedIngredientCommand.getIngredientID());
		return "redirect:/recipe/" + savedIngredientCommand.getRecipeID() + "/ingredients/"
				+ savedIngredientCommand.getIngredientID() + "/show/";
	}

}
