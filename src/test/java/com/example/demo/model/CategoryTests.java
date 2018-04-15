package com.example.demo.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.demo.model.Category;

public class CategoryTests {

	Category category;
	@Before
	public void setup() {
		category =new Category();
	}
	@Test
	public void getCatgID() throws Exception {
     Long expectedCatId =4L;
     category.setCatgID(expectedCatId);
     assertEquals(expectedCatId, category.getCatgID());
		
	}

	@Test
	public void getDescription() throws Exception {

	}

	@Test
	public void getRecipe() throws Exception {

	}
}
