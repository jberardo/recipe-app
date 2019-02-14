package io.joca.recipe.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.joca.recipe.commands.IngredientCommand;
import io.joca.recipe.converters.IngredientToIngredientCommand;
import io.joca.recipe.domain.Recipe;
import io.joca.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientToIngredientCommand ingredientToIngredientCommand; 
	private final RecipeRepository recipeRepository;

	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id) {
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		
		if (!recipeOptional.isPresent()) {
			log.error("Recipe id not found. Id: " + recipeId);
		}
		
		Recipe recipe = recipeOptional.get();
		
		Optional<IngredientCommand> ingredientCommandOptional = 
				recipe.getIngredients().stream()
					.filter(i -> i.getId().equals(id))
					.map(i -> ingredientToIngredientCommand.convert(i)).findFirst();
		
		if (!ingredientCommandOptional.isPresent()) {
			log.error("Ingredient not found. Id: " + id);
		}
		
		return ingredientCommandOptional.get();
	}
}