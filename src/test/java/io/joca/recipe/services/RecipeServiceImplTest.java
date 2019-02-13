package io.joca.recipe.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.joca.recipe.converters.RecipeCommandToRecipe;
import io.joca.recipe.converters.RecipeToRecipeCommand;
import io.joca.recipe.domain.Recipe;
import io.joca.recipe.repositories.RecipeRepository;
import io.joca.recipe.services.RecipeServiceImpl;

/**
 * 
 * @author Joao Berardo
 * @since Feb 12 2019
 */
public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
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
