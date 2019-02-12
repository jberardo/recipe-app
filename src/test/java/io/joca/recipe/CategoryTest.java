package io.joca.recipe;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import io.joca.recipe.domain.Category;
import io.joca.recipe.domain.Recipe;

public class CategoryTest {

	Category category;
	
	@Before
	public void setUp() {
		category = new Category();
	}
	
	@Test
	public void testGetId() {
		Long idValue = 4L;
		category.setId(idValue);
		
		assertEquals(idValue, category.getId());
	}

	@Test
	public void testGetDescription() {
		String description = "Something";
		category.setDescription(description);
		
		assertEquals(description, category.getDescription());
	}
}
