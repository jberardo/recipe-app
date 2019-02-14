package io.joca.recipe.services;

import java.util.Set;

import io.joca.recipe.commands.UnitOfMeasureCommand;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 13, 2019
 *
 */
public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}