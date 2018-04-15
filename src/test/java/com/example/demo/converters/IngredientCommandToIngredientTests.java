package com.example.demo.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.example.demo.commands.IngredientCommand;
import com.example.demo.commands.UnitOfMeasurementCommand;
import com.example.demo.model.Ingredient;


public class IngredientCommandToIngredientTests {
	IngredientCommandToIngredient ingredientCommandToIngredient;
	private final static Long ID_VALUE = new Long(1L);
	private final static Long UOM_ID = new Long(1L);
	private final static String DESCRIPTION = "DCRPTION";
	private final static BigDecimal AMOUNT = new BigDecimal("2");

	@Before
	public void setup() {
		ingredientCommandToIngredient = new IngredientCommandToIngredient(
				new UnitOfMeaurementCommandToUnitOfMesurement());
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(ingredientCommandToIngredient.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(ingredientCommandToIngredient.convert(new IngredientCommand()));
	}

	@Test
	public void testConvert() throws Exception {

		// given
		final IngredientCommand ingredientCommand = new IngredientCommand();

		ingredientCommand.setAmount(AMOUNT);
		ingredientCommand.setDescription(DESCRIPTION);
		ingredientCommand.setIngredientID(ID_VALUE);
		UnitOfMeasurementCommand unitOfMeasurementCommand = new UnitOfMeasurementCommand();
		unitOfMeasurementCommand.setUomID(UOM_ID);
		ingredientCommand.setUnitOfMeasurement(unitOfMeasurementCommand);

		// when
		Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);

		// then
		assertNotNull(ingredient);
		assertNotNull(ingredient.getIngredientID());
		assertEquals(AMOUNT, ingredient.getAmount());

	}

}
