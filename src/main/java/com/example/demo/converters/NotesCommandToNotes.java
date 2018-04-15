package com.example.demo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.commands.NotesCommand;
import com.example.demo.model.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

	@Override
	public Notes convert(NotesCommand source) {
		// TODO Auto-generated method stub
		if (source == null)
			return null;

		final Notes notes = new Notes();
		notes.setNotesID(source.getNotesID());
		notes.setRecipeNotes(source.getRecipeNotes());
		return notes;
	}

}
