package com.example.demo.respositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.UnitOfMeasurement;

public interface UnitOfMeasurementRepository extends CrudRepository<UnitOfMeasurement, Long> {
	Optional<UnitOfMeasurement> findByDescription(String description);
}
