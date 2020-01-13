package si.kuharskimojster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.kuharskimojster.repositories.RecipeRepository;

import java.util.List;

@Service
public class RecipeService{

    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional(readOnly = true)
    public double getRecipeAvgRatings(Long recipeId){
        return recipeRepository.getRecipeAvgRating(recipeId);
    }

    @Transactional(readOnly = true)
    public List<String> getRecipeComments(Long recipeId, int limit){
        return recipeRepository.getRecipeComments(recipeId, limit);
    }


}
