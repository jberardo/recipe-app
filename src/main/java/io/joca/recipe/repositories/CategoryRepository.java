package io.joca.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import io.joca.recipe.domain.Category;

/**
 * 
 * @author Joao Berardo
 * @since Feb 10 2019
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
}