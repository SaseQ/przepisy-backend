package it.marczuk.przepisybackend.model;

import it.marczuk.przepisybackend.repository.RecipeType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
public class Recipe {

    @Id
    private String id;
    private String name;
    private Map<String, String> components;
    private String instruction;
    private RecipeType type;
    private String imageURL;
}
