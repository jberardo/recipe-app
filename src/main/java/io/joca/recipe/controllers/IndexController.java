package io.joca.recipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.joca.recipe.domain.Category;
import io.joca.recipe.domain.UnitOfMeasure;
import io.joca.recipe.repositories.CategoryRepository;
import io.joca.recipe.repositories.UnitOfMeasureRepository;

/**
 * @author Joao Berardo
 * @since Feb 10 2019
 */
@Controller
public class IndexController {
	
	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository uomRepository;
	
	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository uomRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.uomRepository = uomRepository;
	}

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage() {
		
		Optional<Category> optCat = categoryRepository.findByDescription("Itadlian");
		Optional<UnitOfMeasure> optUom = uomRepository.findByDescription("Teaspoon");
		
		optCat.ifPresent(c -> {
			System.out.println("cat id: " + c.getId());			
		});
		
		optUom.ifPresent(uom -> {
			System.out.println("uom: " + uom.getId());			
		});
		
		return "index";
	}
}
