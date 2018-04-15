package com.example.demo.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.UnitOfMeasurement;
import com.example.demo.respositories.UnitOfMeasurementRepository;

//Integration test to check if UnitOfMeasurement repo is working fine.
@RunWith(SpringRunner.class) // provides with springapplicationcontext
@DataJpaTest
public class UnitOfMeasurementRepositoryIT {

	@Autowired
	UnitOfMeasurementRepository unitOfMeasurementRepository;

	@Before
	public void setup() throws Exception {

	}

	@Test
	//@DirtiesContext :commenting out as we do not want to create different contexts for every test.(tests run takes lesser time) 
	public void findByDiscriptionTablespoon() throws Exception {
		Optional<UnitOfMeasurement> uom = unitOfMeasurementRepository.findByDescription("Tablespoon");
		assertEquals("Tablespoon", uom.get().getDescription());

	}
	@Test
	public void findByDiscriptionDash() throws Exception {
		Optional<UnitOfMeasurement> uom = unitOfMeasurementRepository.findByDescription("Dash");
		assertEquals("Dash", uom.get().getDescription());

	}
	
}
