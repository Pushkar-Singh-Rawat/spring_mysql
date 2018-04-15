package com.example.demo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.demo.commands.IngredientCommand;
import com.example.demo.model.Ingredient;

import lombok.Synchronized;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	private final UnitOfMeasurementToUnitOfMeasurementCommand uom;

	public IngredientToIngredientCommand(UnitOfMeasurementToUnitOfMeasurementCommand uom) {
		this.uom = uom;
	}

	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient source) {
		// TODO Auto-generated method stub
		if (source == null) {
			return null;
		}
		final IngredientCommand command = new IngredientCommand();
		command.setIngredientID(source.getIngredientID());
		command.setAmount(source.getAmount());
		if(source.getRecipe().getRecipeID()!=null){
		command.setRecipeID(source.getRecipe().getRecipeID());
		System.out.println("inside converter---------->"+command.getRecipeID());
		}
		else{
			System.out.println("inside converter recipe not present---------->"+command.getRecipeID());
		}
		command.setUnitOfMeasurement(uom.convert(source.getUnitOfMeasurement()));
		command.setDescription(source.getDescription());
	
		return command;
	}

}
