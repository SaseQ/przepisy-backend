package it.marczuk.przepisybackend;

import it.marczuk.przepisybackend.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class PrzepisyBackendApplication {

    private final RecipeService recipeService;

    public static void main(String[] args) {
        SpringApplication.run(PrzepisyBackendApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initActions() {
        recipeService.deleteAllRecipes();
        recipeService.addAllRecipes();
    }
}
