package org.fst.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.fst.model.Doctors;
import org.fst.model.Recipe;
import org.fst.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {

private static final Logger logger = Logger.getLogger(RecipeController.class);
	
	@Autowired
	private RecipeService recipeService;
	
	@RequestMapping(value="/recipe", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> saveRecipeDetails(@RequestBody Recipe recipe,HttpSession session){
		
		logger.info("employee object: "+recipe.getRecipeName());
		Map<String,String> mapObj =recipeService.saveRecipe(recipe,session);
		
		return mapObj;
	}
	@RequestMapping(value="/recipes", method=RequestMethod.GET)
	@ResponseBody
	public List<Recipe> getEmployees(){
		List<Recipe> employeeList = recipeService.getRecipes();
		return employeeList;
	}
	
	@RequestMapping(value = "/recipe/{recipeId}",method =RequestMethod.GET)
	@ResponseBody
	public Recipe getDocList(@PathVariable("recipeId") Integer recipeId) {
		Recipe getRecipe = recipeService.getRecipeObject(recipeId);
		return getRecipe;
		
	}

}
