package it.marczuk.przepisybackend.maper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.marczuk.przepisybackend.exception.JsonMapperException;
import it.marczuk.przepisybackend.model.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Component
@Slf4j
public class RecipeJsonMapper {

    public List<Recipe> mapToModel(String jsonURI) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(jsonURI)) {
            Type listType = new TypeToken<List<Recipe>>(){}.getType();
            List<Recipe> dataList = gson.fromJson(reader, listType);
            log.info(dataList.isEmpty() ? "Json map list is empty" : "Json map list: " + jsonURI);
            return dataList;
        } catch (IOException e) {
            log.error("Json mapper exception");
            throw new JsonMapperException(e.getMessage());
        }
    }
}
