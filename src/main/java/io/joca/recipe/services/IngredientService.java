package io.joca.recipe.services;

import io.joca.recipe.commands.IngredientCommand;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 13, 2019
 *
 */
public interface IngredientService {
	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);
	IngredientCommand saveIngredientCommand(IngredientCommand command);
	void deleteById(Long recipeId, Long idToDelete);
}