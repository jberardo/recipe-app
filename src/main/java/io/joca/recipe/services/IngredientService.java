package io.joca.recipe.services;

import java.util.Set;

import io.joca.recipe.commands.IngredientCommand;
import io.joca.recipe.domain.Ingredient;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 13, 2019
 *
 */
public interface IngredientService {
	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);
	IngredientCommand saveIngredientCommand(IngredientCommand command);
}