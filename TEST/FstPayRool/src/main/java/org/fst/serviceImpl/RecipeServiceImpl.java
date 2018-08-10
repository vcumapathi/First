package org.fst.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.fst.model.Recipe;
import org.fst.repository.RecipeRepository;
import org.fst.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;
	
	public Map<String, String> saveRecipe(Recipe recipe, HttpSession session) {
		Map<String,String> saveObj=recipeRepository.saveRecipe(recipe);
		return saveObj;
	}

	public List<Recipe> getRecipes() {
		List<Recipe> empList = recipeRepository.getRecipes();
		return empList;
	}

	public Recipe getRecipeObject(Integer recipeId) {
		Recipe recipeObj = recipeRepository.getRecipeObject(recipeId);
		return recipeObj;
	}

}
