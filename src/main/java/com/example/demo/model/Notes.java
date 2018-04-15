package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notesID;
	@Lob //now user can bypass the hibernate's default limit of 255 chars.
	private String recipeNotes; //will be saved as a CLOB
	@OneToOne // no cascading as we have designed Recipe to be the owner of this bean. If we
				// decide to delete notes then that would not impact Recipe object.
	private Recipe recipe;

	public Notes() {
		super();
		// TODO Auto-generated constructor stub
	}
/*	public String getRecipeNotes() {
		return recipeNotes;
	}

	public void setRecipeNotes(String recipeNotes) {
		this.recipeNotes = recipeNotes;
	}


	public Long getNotesID() {
		return notesID;
	}

	public void setNotesID(Long notesID) {
		this.notesID = notesID;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}*/
}
