package io.joca.recipe.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.joca.recipe.domain.Recipe;
import io.joca.recipe.repositories.RecipeRepository;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 12, 2019
 *
 */
public class RecipeServiceImplTest {

	private RecipeServiceImpl service;
	
	@Mock
	private RecipeRepository repository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		service = new RecipeServiceImpl(repository);
	}
	
	@Test
	public void getRecipeByIdTest() {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(repository.findById(anyLong())).thenReturn(recipeOptional);
		
		Recipe recipeReturn = service.findById(2L);
		
		assertNotNull("Null recipe returned", recipeReturn);
		
		verify(repository, times(1)).findById(anyLong());
		verify(repository, never()).findAll();
	}

	@Test
	public void getRecipesTest() {
		Recipe recipe = new Recipe();
		HashSet recipesData = new HashSet();
		recipesData.add(recipe);
		
		when(service.getRecipes()).thenReturn(recipesData);
		
		Set<Recipe> recipes = service.getRecipes();
		
		assertEquals(recipes.size(), 1);
		
		verify(repository, times(1)).findAll();
		verify(repository, never()).findById(anyLong());
	}
}