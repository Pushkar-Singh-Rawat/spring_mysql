package com.example.demo.services;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Recipe;
import com.example.demo.respositories.RecipeRepository;

public class ImageServiceImplTests {

	@Mock
	RecipeRepository recipeRepository;

	ImageServiceImpl imageServiceImpl;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		imageServiceImpl=new ImageServiceImpl(recipeRepository);
	}
	@Test
	public void testSaveUploadImageFile() throws Exception{
		Long id=1L;
		Recipe recipe=new Recipe();
		recipe.setRecipeID(id);
		MultipartFile multipartFile=new MockMultipartFile("recipeImageFile", "test.txt", "text/plain", "spring recipe app".getBytes());
		Optional<Recipe> recipeOptional=Optional.of(recipe);
		Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);

		ArgumentCaptor<Recipe> argumentCaptor=ArgumentCaptor.forClass(Recipe.class);
		//when
		imageServiceImpl.saveImageFile(id, multipartFile);
		//then
		Mockito.verify(recipeRepository,Mockito.times(1)).save(argumentCaptor.capture());
		Recipe savedRecipe= argumentCaptor.getValue();
		assertEquals(multipartFile.getBytes().length,savedRecipe.getImage().length);
	}

}
