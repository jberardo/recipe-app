package io.joca.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.joca.recipe.domain.UnitOfMeasure;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 10, 2019
 *
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
	
	Optional<UnitOfMeasure> findByDescription(String description);
}