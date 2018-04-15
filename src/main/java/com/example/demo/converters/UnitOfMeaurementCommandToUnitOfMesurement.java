package com.example.demo.converters;

import org.springframework.stereotype.Component;

import com.example.demo.commands.UnitOfMeasurementCommand;
import com.example.demo.model.UnitOfMeasurement;

import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

@Component
public class UnitOfMeaurementCommandToUnitOfMesurement implements Converter<UnitOfMeasurementCommand,UnitOfMeasurement>{

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasurement convert(UnitOfMeasurementCommand source) {
		// TODO Auto-generated method stub
		if(source==null){
			return null;
		}
	final UnitOfMeasurement uom=new UnitOfMeasurement();
		uom.setUomID(source.getUomID());
		uom.setDescription(source.getDescription());
		return uom;
	}

	
}

