package com.example.demo.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.http.server.reactive.MockServerHttpResponse;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.commands.RecipeCommand;
import com.example.demo.respositories.RecipeRepository;
import com.example.demo.services.ImageService;
import com.example.demo.services.RecipeService;

public class ImageControllerTests {
	@Mock
	ImageService imageService;

	@Mock
	RecipeService recipeService;

	ImageController imageController; // we will test the handler methods of this
	// controller class.
	MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this); // mocks: imageService and
		// recipeService
		imageController = new ImageController(imageService, recipeService); // DI
		// for
		// imageService
		// and
		// recipeService
		mockMvc = MockMvcBuilders.standaloneSetup(imageController).build(); // standalone
		// mockMvc
		// object
		// created.
	}

	@Test
	public void testGetUploadImageForm() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setRecipeID(1L);

		Mockito.when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(command);

		mockMvc.perform(get("/recipe/1/image")).andExpect(status().isOk())
		.andExpect(model().attributeExists("recipe"));

		Mockito.verify(recipeService, Mockito.times(1)).findCommandById(Mockito.anyLong());

	}

	@Test
	public void testHandleImagePost() throws Exception {
		MockMultipartFile mockMultipartFile = new MockMultipartFile("recipeImagefile", "testing.txt", "text/plain",
				"recipe boot app".getBytes());
		mockMvc.perform(multipart("/recipe/1/image")
				.file(mockMultipartFile))
		.andExpect(status().is3xxRedirection())
		.andExpect(header().string("Location", "/recipe/1/show"));
		Mockito
		.verify(imageService,Mockito.times(1))
		.saveImageFile(Mockito.anyLong(),Mockito.any());

	}

	@Test
	public void testRenderImageFromDB() throws Exception{

		RecipeCommand recipeCommand=new RecipeCommand();
		recipeCommand.setRecipeID(1L);
		String str="Spring boot recipe App";
		Byte[] byteArr=new Byte[str.getBytes().length];
		int i=0;
		for(byte b: str.getBytes()){
			byteArr[i++]=b;
		}
		recipeCommand.setImage(byteArr);

		Mockito.when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(recipeCommand);

		//when
		MockHttpServletResponse response=mockMvc.perform(get("/recipe/1/recipeimage"))
				.andExpect(status().isOk())
				.andReturn().getResponse();
		byte[] retrivedImagefile=response.getContentAsByteArray();

		assertEquals(str.getBytes().length,retrivedImagefile.length);

	}

}
