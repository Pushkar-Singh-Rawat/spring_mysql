package com.example.demo.commands;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Setter
@Getter
@NoArgsConstructor
public class NotesCommand {
	private Long notesID;

	private String recipeNotes;
}
