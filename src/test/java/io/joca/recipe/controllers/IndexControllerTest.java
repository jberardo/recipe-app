package io.joca.recipe.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import io.joca.recipe.controllers.IndexController;
import io.joca.recipe.domain.Recipe;
import io.joca.recipe.services.RecipeService;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 12, 2019
 *
 */
public class IndexControllerTest {

	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;
	
	IndexController controller;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		controller = new IndexController(recipeService);
	}

	@Test
	public void testMockMvc() throws Exception {
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		
		mock.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"));
	}
	
	@Test
	public void testGetIndexPage() {
		
		// given
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(new Recipe());
		
		Recipe recipe = new Recipe();
		recipe.setId(12L);
		recipes.add(recipe);
		
		when(recipeService.getRecipes()).thenReturn(recipes);
		
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
		// when
		String viewName = "index";
		String result = controller.getIndexPage(model);
		
		// then
		assertEquals(viewName, result);
		verify(recipeService, times(1)).getRecipes();
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		assertEquals(2, argumentCaptor.getValue().size());
	}
}