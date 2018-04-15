package com.example.demo.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.commands.RecipeCommand;
import com.example.demo.services.ImageService;
import com.example.demo.services.RecipeService;

@Controller
public class ImageController {

	private ImageService imageService;
	private RecipeService recipeService;
	public ImageController(ImageService imageService, RecipeService recipeService) {
		// TODO Auto-generated constructor stub
		this.imageService=imageService;
		this.recipeService=recipeService;
	}

	@GetMapping("/recipe/{id}/image")
	public String getUploadImageForm(@PathVariable String id,Model model){
		RecipeCommand recipe=recipeService.findCommandById(Long.valueOf(id));
		//need to add image blob variable in recipe and recipeCommand and in recipe converters too.
		model.addAttribute("recipe",recipe);
		return "recipe/uploadImageform";
	}
	
	@PostMapping("/recipe/{id}/image")
	public String handleImageUpload(@PathVariable String id, @RequestParam("recipeImagefile") MultipartFile file){
		imageService.saveImageFile(Long.valueOf(id), file);	
		return "redirect:/recipe/"+id+"/show";
	}
	@GetMapping("/recipe/{id}/recipeimage")
	public void renderImageFromDB(@PathVariable String id,HttpServletResponse response) throws IOException{
		RecipeCommand command=recipeService.findCommandById(Long.valueOf(id));
		if(command.getImage()!=null){
		byte[] byteArr=new byte[command.getImage().length];
		int i=0;
		for(byte b:command.getImage()){
			byteArr[i++]=b;
		}
		response.setContentType("image/jpeg");
		InputStream is=new ByteArrayInputStream(byteArr);
		IOUtils.copy(is, response.getOutputStream()); //is will be copied to the response.
		}
	}
	
}
