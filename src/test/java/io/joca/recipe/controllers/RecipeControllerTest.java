package io.joca.recipe.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import io.joca.recipe.domain.Recipe;
import io.joca.recipe.services.RecipeService;

public class RecipeControllerTest {

	@Mock
	RecipeService service;
	
	RecipeController controller;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		controller = new RecipeController(service);
	}

	@Test
	public void testGetRecipeById() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		
		when(service.findById(anyLong())).thenReturn(recipe);
		
		mock.perform(get("/recipe/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/show"));
	}
}