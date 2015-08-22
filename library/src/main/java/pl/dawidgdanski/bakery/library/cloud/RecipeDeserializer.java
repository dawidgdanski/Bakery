package pl.dawidgdanski.bakery.library.cloud;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import pl.dawidgdanski.bakery.library.model.Ingredient;
import pl.dawidgdanski.bakery.library.model.Recipe;
import pl.dawidgdanski.bakery.library.model.RecipeImpl;
import pl.dawidgdanski.bakery.library.model.Step;

final class RecipeDeserializer extends AbstractDeserializer<Recipe> {

    @Override
    public Recipe deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = (JsonObject) json;

        String id = getString(jsonObject.get(JSONConstants.JSON_ID), null);
        String title = getString(jsonObject.get(JSONConstants.JSON_TITLE), null);
        String description = getString(jsonObject.get(JSONConstants.JSON_DESCRIPTION), null);

        JsonArray elements = getJsonArray(jsonObject.get(JSONConstants.JSON_INGREDIENTS));

        Type ingredientType = new TypeToken<Ingredient>(){ }.getType();

        Collection<Ingredient> ingredients = new ArrayList<Ingredient>(elements.size());

        for(JsonElement element : elements) {
            Ingredient ingredient = context.deserialize(element, ingredientType);
            ingredients.add(ingredient);
        }

        JsonArray stepsArray = getJsonArray(jsonObject.get(JSONConstants.JSON_STEPS));

        Type stepType = new TypeToken<Step>(){ }.getType();

        Collection<Step> steps = new ArrayList<Step>();

        for(JsonElement stepJson : stepsArray) {
            Step step = context.deserialize(stepJson, stepType);
            steps.add(step);
        }

        return new RecipeImpl.Builder()
                .setId(id)
                .addSteps(steps)
                .addIngredients(ingredients)
                .setDescription(description)
                .setTitle(title)
                .build();
    }
}
