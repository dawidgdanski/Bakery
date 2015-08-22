package pl.dawidgdanski.bakery.library.cloud.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
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

public final class RecipeDeserializer extends AbstractDeserializer<Recipe> {

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

        JsonElement imageUrlElement = jsonObject.get(JSONConstants.JSON_IMAGES);
        String imageUrl = getImageUrl(imageUrlElement);

        return new RecipeImpl.Builder()
                .setId(id)
                .addIngredients(ingredients)
                .setDescription(description)
                .setImageUrl(imageUrl)
                .setTitle(title)
                .build();
    }

    private String getImageUrl(final JsonElement jsonElement) {
        if(jsonElement.isJsonNull()) {
            return null;
        }

        if(jsonElement.isJsonArray()) {
            JsonArray jsonArray = (JsonArray) jsonElement;

            JsonObject firstImageUrlObject = (JsonObject) jsonArray.get(0);

            return getString(firstImageUrlObject.get(JSONConstants.JSON_URL), null);
        }

        return null;
    }
}
