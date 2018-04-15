package com.example.demo.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.example.demo.commands.UnitOfMeasurementCommand;
import com.example.demo.model.UnitOfMeasurement;

public class UnitOfMeasurementCommandToUnitOfMeasurementTests {

	private static final String DESCRIPTION = "description";
	private static final Long LONG_VALUE = new Long(1L);

	UnitOfMeaurementCommandToUnitOfMesurement converter;

	@Before
	public void setup() {
		converter = new UnitOfMeaurementCommandToUnitOfMesurement();

	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new UnitOfMeasurementCommand()));
	}

	@Test
	public void testConverter() throws Exception {
		// given
		UnitOfMeasurementCommand command = new UnitOfMeasurementCommand();
		command.setUomID(LONG_VALUE);
		command.setDescription(DESCRIPTION);
		// when
		UnitOfMeasurement uom = converter.convert(command);

		// then
		assertNotNull(uom);
		assertEquals(LONG_VALUE, uom.getUomID());
		assertEquals(DESCRIPTION, uom.getDescription());

	}
}
