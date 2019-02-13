package io.joca.recipe.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.joca.recipe.commands.RecipeCommand;
import io.joca.recipe.converters.RecipeCommandToRecipe;
import io.joca.recipe.converters.RecipeToRecipeCommand;
import io.joca.recipe.domain.Recipe;
import io.joca.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 10, 2019
 *
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
	
	private RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Set<Recipe> getRecipes() {
		
		log.debug("Service method");
		
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}
	
	@Override
	public Recipe findById(Long id) {

		Optional<Recipe> recipe = recipeRepository.findById(id);
		
		if (!recipe.isPresent()) {
			throw new RuntimeException("Recipe not found");
		}
		
		return recipe.get();
	}
	
    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }
    
    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
