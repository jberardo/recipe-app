package io.joca.recipe.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 14, 2019
 *
 */
public interface ImageService {
	void saveImageFile(Long recipeId, MultipartFile file);
}