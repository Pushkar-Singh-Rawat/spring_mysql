package com.example.demo.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.demo.commands.UnitOfMeasurementCommand;
import com.example.demo.converters.UnitOfMeasurementToUnitOfMeasurementCommand;
import com.example.demo.respositories.UnitOfMeasurementRepository;

@Service
public class UnitOfMeasurementImpl implements UnitOfMeasurementService {

	private UnitOfMeasurementRepository unitOfMeasurementRepository;
	private UnitOfMeasurementToUnitOfMeasurementCommand UomToUomCommand;
	public UnitOfMeasurementImpl(UnitOfMeasurementRepository unitOfMeasurementRepository,UnitOfMeasurementToUnitOfMeasurementCommand UomToUomCommand) {
		// TODO Auto-generated constructor stub
		this.unitOfMeasurementRepository=unitOfMeasurementRepository;
		this.UomToUomCommand=UomToUomCommand;
	}

	@Override
	public Set<UnitOfMeasurementCommand> listAllUoms() {
		// TODO Auto-generated method stub
		//need to get a set of unitOfMeasurementCommand objects
		return StreamSupport.stream(unitOfMeasurementRepository.findAll().spliterator(),false)
				.map(UomToUomCommand::convert)
				.collect(Collectors.toSet());
	}

}
