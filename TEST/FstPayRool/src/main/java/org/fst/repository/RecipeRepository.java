package org.fst.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.fst.common.ApplicationConstants;
import org.fst.model.Category;
import org.fst.model.Recipe;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class RecipeRepository extends BaseRepository {
	private static final Logger logger = Logger.getLogger(RecipeRepository.class);
	
	public Map<String, String> saveRecipe(Recipe recipe) {

		Map<String,String> mapObj = new HashMap<String, String>();
		Session session = getSession();
		Transaction transaction=session.beginTransaction();
		Recipe recipeObj = null;
		try{
		recipeObj = new Recipe();
		recipeObj.setRecipeName(recipe.getRecipeName());
		recipeObj.setIngredientOne(recipe.getIngredientOne());
		recipeObj.setIngredientTwo(recipe.getIngredientTwo());
		recipeObj.setIngredientThree(recipe.getIngredientThree());
		recipeObj.setIngredientFour(recipe.getIngredientFour());
		session.saveOrUpdate(recipeObj);
		transaction.commit();
		mapObj.put(ApplicationConstants.STATUS, ApplicationConstants.SAVED_SUCCESS);	
		mapObj.put(ApplicationConstants.DESCRIPTION, ApplicationConstants.DATA_FOUND);
		}catch (Exception e) {
			e.printStackTrace();
			mapObj.put(ApplicationConstants.STATUS, ApplicationConstants.FAILED);
			mapObj.put(ApplicationConstants.DESCRIPTION, ApplicationConstants.ERROR);
			transaction.rollback();
			logger.info("Exception ",e);
		}finally {
			
			session.close();
		}
		return mapObj;
	}

	public List<Recipe> getRecipes() {
			Session session = getSession();
			Criteria cr = session.createCriteria(Recipe.class)
					.setProjection(Projections.projectionList().add(Projections.property("recipeId"), "recipeId")
							.add(Projections.property("recipeName"), "recipeName"))
					.setResultTransformer(Transformers.aliasToBean(Recipe.class));
			List<Recipe> employees = cr.list();
			return employees;
		}

	public Recipe getRecipeObject(Integer recipeId) {

		Recipe categoryObj = null;
		Session session = getSession();
		try{
			categoryObj = new Recipe();
			Criteria cr = session.createCriteria(Recipe.class);
			cr.add(Restrictions.eq("recipeId", recipeId));
			List<Recipe> result = cr.list();
			categoryObj = result.get(0);
			session.clear();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return categoryObj;
	
	/*	Recipe recipe = new Recipe();
		Session session = getSession();
		Criteria critera = session.createCriteria(Recipe.class);
		critera.add(Restrictions.eq("recipeId",recipeId));
		Object obj = critera.uniqueResult();
		System.out.println("object :"+obj);
		recipe =(Recipe) obj;
		if(recipe !=null){
			return recipe;
		}else{
			System.out.println("error...");
		}
		return recipe;*/
	
//		Recipe recipe = new Recipe();
//		List<Recipe> getRecords = new ArrayList<Recipe>();
//		String getCategory ="From Recipe where recipeId =:recipeId";
//		Query queryObj = sessionFactory.getCurrentSession().createQuery(getCategory);
//		queryObj.setParameter("recipeId",recipeId);
//		getRecords =queryObj.list();
//		recipe =getRecords.get(0);
//		return recipe;
	}

}
