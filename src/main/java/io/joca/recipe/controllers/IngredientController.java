package io.joca.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.joca.recipe.commands.IngredientCommand;
import io.joca.recipe.commands.RecipeCommand;
import io.joca.recipe.commands.UnitOfMeasureCommand;
import io.joca.recipe.services.IngredientService;
import io.joca.recipe.services.RecipeService;
import io.joca.recipe.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 13, 2019
 *
 */
@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService uomService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }
    
    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
    	log.debug("Showing ingredient: " + id + " for recipe: " + recipeId);
    	
    	model.addAttribute("ingredient", ingredientService
    			.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
    	
    	return "recipe/ingredient/show";
    }
    
    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {
    	
    	RecipeCommand recipe = recipeService.findCommandById(Long.valueOf(recipeId));
    	
    	//need to return back parent id for hidden form property
    	IngredientCommand ingredient = new IngredientCommand();
    	ingredient.setRecipeId(recipe.getId());
    	model.addAttribute("ingredient", ingredient);

    	ingredient.setUom(new UnitOfMeasureCommand());
    	model.addAttribute("uomList", uomService.listAllUoms());
    	
    	return "recipe/ingredient/ingredientform";
    }
    
    @GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
    				@PathVariable String id, Model model) {
    	
    	model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
    	model.addAttribute("uomList", uomService.listAllUoms());
    	return "recipe/ingredient/ingredientform";
    }
    
    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
    	
    	IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
    	
    	log.debug("saved recipe id: " + savedCommand.getRecipeId());
    	log.debug("saved ingredient id: " + savedCommand.getId());
    	
    	return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
    
    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id){

        log.debug("deleting ingredient id:" + id);
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}