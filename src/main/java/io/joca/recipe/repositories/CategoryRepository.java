package io.joca.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.joca.recipe.domain.Category;

/**
 * 
 * @author Joao Berardo
 * @since Feb 10 2019
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	Optional<Category> findByDescription(String description);
}