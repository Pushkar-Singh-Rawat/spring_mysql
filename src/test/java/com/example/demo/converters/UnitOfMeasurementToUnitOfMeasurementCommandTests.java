package com.example.demo.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.example.demo.commands.UnitOfMeasurementCommand;
import com.example.demo.model.UnitOfMeasurement;

public class UnitOfMeasurementToUnitOfMeasurementCommandTests {
	UnitOfMeasurementToUnitOfMeasurementCommand converter;
	private static final String DESCRIPTION="some uom description";
	private static final Long LONG_VALUE=new Long(1L);
	
	@Before
	public void setup() {
		 converter=new UnitOfMeasurementToUnitOfMeasurementCommand();
	}
	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new UnitOfMeasurement()));
	}

	@Test
	public void testConverter() throws Exception {
		// given
		UnitOfMeasurement uom = new UnitOfMeasurement();
		uom.setUomID(LONG_VALUE);
		uom.setDescription(DESCRIPTION);
		// when
		UnitOfMeasurementCommand command = converter.convert(uom);

		// then
		assertNotNull(command);
		assertEquals(LONG_VALUE, command.getUomID());
		assertEquals(DESCRIPTION, command.getDescription());

	}
	
}
