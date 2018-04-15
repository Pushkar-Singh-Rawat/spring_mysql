package com.example.demo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Recipe;
import com.example.demo.respositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

	private RecipeRepository recipeRepository;
	public ImageServiceImpl(RecipeRepository recipeRepository) {
		// TODO Auto-generated constructor stub
		this.recipeRepository=recipeRepository;
	}

	@Override
	@Transactional
	public void saveImageFile(Long recipeID, MultipartFile imageFile) {
	
		try{
		Recipe recipe=recipeRepository.findById(recipeID).get();
		Byte[] byteObjects=new Byte[imageFile.getBytes().length];
		//imageFile is of byte type
		int i=0;
		for(byte b:imageFile.getBytes()){
			
			byteObjects[i++]=b;
			
			}
		recipe.setImage(byteObjects);
		recipeRepository.save(recipe);
		}
		catch(Exception e){
		log.error("Error occured: ",e);
		e.printStackTrace();
		}
	}

}
