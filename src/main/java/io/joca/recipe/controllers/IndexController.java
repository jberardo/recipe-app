package io.joca.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Joao Berardo
 * @since Feb 10 2019
 */
@Controller
public class IndexController {
	
	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage() {
		return "index";
	}
}
