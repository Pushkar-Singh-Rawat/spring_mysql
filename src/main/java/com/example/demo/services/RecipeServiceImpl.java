package com.example.demo.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.commands.RecipeCommand;
import com.example.demo.converters.RecipeCommandToRecipe;
import com.example.demo.converters.RecipeToRecipeCommand;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Recipe;
import com.example.demo.respositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipe() {
		// TODO Auto-generated method stub
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	public Recipe findById(Long l) {
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(l);
		if (!recipeOptional.isPresent()) {
			throw new NotFoundException("recipe with ID:"+l.toString()+" could not be found.");
		}
		
		return recipeOptional.get();
	}

	@Transactional
	@Override
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		// TODO Auto-generated method stub
		Recipe pojoRecipe = recipeCommandToRecipe.convert(recipeCommand);//detached from hibernate context. Just a pojo.
		Recipe savedRecipe = recipeRepository.save(pojoRecipe); //new entry if not already else merge with exisiting.
		log.debug("saved recipeID::" + savedRecipe.getRecipeID());

		return recipeToRecipeCommand.convert(savedRecipe);
	}

	@Transactional
	@Override
	public RecipeCommand findCommandById(Long recipeCommandID) {
	return recipeToRecipeCommand.convert(findById(recipeCommandID));
		
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		/*RecipeCommand recipeCommand=findCommandById(recipeCommandID);
		Recipe recipe=recipeCommandToRecipe.convert(recipeCommand);*/
		log.debug("id being deleted "+ id);
		recipeRepository.deleteById(id);
	}

}
