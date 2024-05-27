package it.marczuk.przepisybackend.repository;

import it.marczuk.przepisybackend.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepo extends MongoRepository<Recipe, String> {

    Recipe findRecipeByType(RecipeType recipeType);

    List<Recipe> findAllByType(RecipeType recipeType);
}
