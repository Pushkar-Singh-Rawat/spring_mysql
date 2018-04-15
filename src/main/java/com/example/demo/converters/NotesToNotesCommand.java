package com.example.demo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.commands.NotesCommand;
import com.example.demo.model.Notes;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

	@Override
	public NotesCommand convert(Notes source) {
		// TODO Auto-generated method stub
		if (source == null)
			return null;

		final NotesCommand notesCommand = new NotesCommand();
		notesCommand.setNotesID(source.getNotesID());
		notesCommand.setRecipeNotes(source.getRecipeNotes());
		return notesCommand;
	}

}
