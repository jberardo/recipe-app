package io.joca.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.joca.recipe.commands.RecipeCommand;
import io.joca.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 12, 2019
 *
 */
@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

	private final RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping("/{id}/show")
	public String getRecipeById(@PathVariable String id, Model model) {
		
		model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
		
		return "recipe/show";
	}
	
	@RequestMapping("/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		
		return "recipe/recipeform";
	}

    @RequestMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return  "recipe/recipeform";
    }
	
    @PostMapping({ "", "/" })
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		if (command == null) throw new RuntimeException("Invalid Recipe!");
		RecipeCommand recipe = recipeService.saveRecipeCommand(command);
		
		return "redirect:/recipe/" + recipe.getId() + "/show";
	}
}