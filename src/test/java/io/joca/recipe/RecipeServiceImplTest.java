package io.joca.recipe;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.joca.recipe.domain.Recipe;
import io.joca.recipe.repositories.RecipeRepository;
import io.joca.recipe.services.RecipeServiceImpl;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	public void testGetRecipes() {
		Recipe recipe1 = new Recipe();
		Recipe recipe2 = new Recipe();
		HashSet<Recipe> recipeData = new  HashSet<>();
		Set<Recipe> recipes = new HashSet<>();

		recipe1.setDescription("Recipe 1");
		recipe2.setDescription("Recipe 2");

		recipeData.add(recipe1);
		recipeData.add(recipe2);
		recipeData.add(new Recipe());
		
		when(recipeService.getRecipes()).thenReturn(recipeData);
		
		recipes = recipeService.getRecipes();
		
		assertEquals(recipes.size(), 3);
		verify(recipeRepository, times(1)).findAll();
	}
}
