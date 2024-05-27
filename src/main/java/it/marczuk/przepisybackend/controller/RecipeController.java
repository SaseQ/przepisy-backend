package it.marczuk.przepisybackend.controller;

import it.marczuk.przepisybackend.model.Recipe;
import it.marczuk.przepisybackend.repository.RecipeType;
import it.marczuk.przepisybackend.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/random")
    public ResponseEntity<Recipe> getRecipeByType(@RequestParam String type) {
        RecipeType recipeType;
        try {
            recipeType = RecipeType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).build();
        }
        Recipe recipe = recipeService.getRandomRecipe(recipeType);
        return ResponseEntity.ok(recipe);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initActions() {
        recipeService.deleteAllRecipes();
        recipeService.addAllRecipes();
    }
}
