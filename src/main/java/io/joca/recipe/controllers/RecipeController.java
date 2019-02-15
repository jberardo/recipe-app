package io.joca.recipe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import io.joca.recipe.commands.RecipeCommand;
import io.joca.recipe.exceptions.NotFoundException;
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

	@GetMapping("/{id}/show")
	public String getRecipeById(@PathVariable String id, Model model) {
		
		model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
		
		return "recipe/show";
	}
	
	@GetMapping("/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		
		return "recipe/recipeform";
	}

	@GetMapping("/{id}/update")
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
    
    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception ex) {
    	
    	log.error("Handling not found exception");
    	log.error(ex.getMessage());
    	
    	ModelAndView mav = new ModelAndView();

    	mav.setViewName("404error");
    	mav.addObject("ex", ex);
    	
    	return mav;
    }
}