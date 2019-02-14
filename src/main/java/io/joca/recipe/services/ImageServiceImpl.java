package io.joca.recipe.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.joca.recipe.domain.Recipe;
import io.joca.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Joao Berardo
 * @since Feb. 14, 2019
 *
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
	
	private final RecipeRepository recipeRepository;

	public ImageServiceImpl( RecipeRepository recipeService) {
		this.recipeRepository = recipeService;
	}
    
    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);
            recipeRepository.save(recipe);
            
        } catch (IOException e) {
            //TODO: handle better
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}