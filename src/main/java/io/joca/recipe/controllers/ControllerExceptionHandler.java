package io.joca.recipe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 15, 2019
 *
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleBadRequest(Exception ex) {
    	
    	log.error("Id must be a valid number");
    	log.error(ex.getMessage());
    	
    	ModelAndView mav = new ModelAndView();

    	mav.setViewName("400error");
    	mav.addObject("ex", ex);
    	
    	return mav;
    }
}