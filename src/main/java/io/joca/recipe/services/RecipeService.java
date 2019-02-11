package io.joca.recipe.services;

import java.util.Set;

import io.joca.recipe.domain.Recipe;

/**
 * 
 * @author Joao Berardo
 * @since Feb 10 2019
 *
 */
public interface RecipeService {
	
	public Set<Recipe> getRecipes();
}