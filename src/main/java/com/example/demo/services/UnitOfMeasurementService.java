package com.example.demo.services;

import java.util.Set;

import com.example.demo.commands.UnitOfMeasurementCommand;

public interface UnitOfMeasurementService {

	Set<UnitOfMeasurementCommand> listAllUoms();

}
