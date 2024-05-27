package it.marczuk.przepisybackend.service;

import it.marczuk.przepisybackend.maper.RecipeJsonMapper;
import it.marczuk.przepisybackend.model.Recipe;
import it.marczuk.przepisybackend.repository.RecipeRepo;
import it.marczuk.przepisybackend.repository.RecipeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeService {

    private final RecipeRepo recipeRepo;
    private final RecipeJsonMapper recipeJsonMapper;

    public Recipe getRandomRecipe(RecipeType type) {
        List<Recipe> recipes = recipeRepo.findAllByType(type);
        return getRandomElement(recipes);
    }

    @Transactional
    public void addAllRecipes() {
        List<Recipe> breakfastRecipes = recipeJsonMapper.mapToModel("breakfast.json");
        addRecipe(breakfastRecipes);
        List<Recipe> lunchRecipes = recipeJsonMapper.mapToModel("lunch.json");
        addRecipe(lunchRecipes);
        List<Recipe> dessertRecipes = recipeJsonMapper.mapToModel("dessert.json");
        addRecipe(dessertRecipes);
        List<Recipe> dinerRecipes = recipeJsonMapper.mapToModel("diner.json");
        addRecipe(dinerRecipes);
        log.info("Added all recipes");
    }

    public void addRecipe(List<Recipe> recipes) {
        recipeRepo.saveAll(recipes);
    }

    public void deleteAllRecipes() {
        recipeRepo.deleteAll();
        log.info("Deleted all recipes");
    }

    private  <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
