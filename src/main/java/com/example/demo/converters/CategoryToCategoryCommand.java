package com.example.demo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.demo.commands.CategoryCommand;
import com.example.demo.model.Category;

import lombok.Synchronized;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

	@Synchronized
	@Nullable
	@Override
	public CategoryCommand convert(Category source) {
		if (source != null) {
			final CategoryCommand command = new CategoryCommand();
			command.setCatgID(source.getCatgID());
			command.setDescription(source.getDescription());
			return command;
		}
		return null;
	}

}
