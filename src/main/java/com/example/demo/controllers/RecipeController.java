package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.commands.RecipeCommand;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.services.RecipeService;
import com.example.demo.services.RecipeServiceImpl;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class RecipeController {

	private RecipeService recipeService;
	private static final String  RECIPE_UPDATE_URL="recipe/recipeform";

	@Autowired // just to indicate that springcontext injects RecipeServiceImpl
				// object here
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping("/recipe/{id}/show") // setting up MVC.
	public String showById(Model model, @PathVariable String id) {
		model.addAttribute("recipe", recipeService.findById(new Long(id)));
		return "recipe/show";
	}
	@GetMapping("/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return RECIPE_UPDATE_URL;
	}

	@GetMapping("/recipe/{id}/update") // setting up MVC.
	public String updateRecipe(Model model, @PathVariable String id) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return RECIPE_UPDATE_URL;
	}

	/*@PostMapping
	@RequestMapping("recipe")*/
	@PostMapping("recipe")
	public String saveOrUpdateRecipe(@Valid @ModelAttribute("recipe") RecipeCommand command,BindingResult bindingResult) {
		//fields property is from bindingResult that we are using in recipeform template
		// @ModalAttribute grabs any post object present in the "recipe" path.
		if(bindingResult.hasErrors()){
			bindingResult.getAllErrors().forEach(error->{
				log.debug(error.toString());
			});
			return RECIPE_UPDATE_URL;
		}
		RecipeCommand savedRecipe = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/" + savedRecipe.getRecipeID() + "/show";
	}

	@GetMapping("/recipe/{id}/delete") // setting up MVC.
	public String deleteRecipe(@PathVariable String id) {
		log.debug("id of recipe to be deleted "+ id);
		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/";
	}
	

	
}
