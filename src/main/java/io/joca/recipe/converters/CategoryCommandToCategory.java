package io.joca.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import io.joca.recipe.commands.CategoryCommand;
import io.joca.recipe.domain.Category;
import lombok.Synchronized;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 12, 2019
 *
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
	
    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
    	
        if (source == null) {
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}