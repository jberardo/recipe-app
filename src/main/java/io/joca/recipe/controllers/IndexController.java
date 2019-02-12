package io.joca.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.joca.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Joao Berardo
 * @since Feb 10 2019
 */
@Slf4j
@Controller
public class IndexController {
	
	private RecipeService recipeService;
	
	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage(Model model) {

		log.info("Called Index Controller");
		
		model.addAttribute("recipes", recipeService.getRecipes());
		
		return "index";
	}
}
