package io.joca.recipe.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import io.joca.recipe.domain.Recipe;
import io.joca.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Joao Berardo
 * @since Feb 10 2019
 *
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
	
	private RecipeRepository recipeRepository;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
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
}
