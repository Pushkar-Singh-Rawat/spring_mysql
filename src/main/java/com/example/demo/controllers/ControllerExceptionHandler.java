package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.exceptions.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler { //global exception handler setup

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFoundException(Exception exception){
		log.error("inside Exception handler");
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("exception",exception);
		modelAndView.setViewName("error404page.html");
		return modelAndView;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleNumberFormatException(Exception exception){
		log.error("inside bad request Exception handler");
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("nfException",exception);
		modelAndView.setViewName("error400page.html");
		return modelAndView;
	}
}
