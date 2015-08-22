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
import java.util.List;

import pl.dawidgdanski.bakery.library.model.Element;
import pl.dawidgdanski.bakery.library.model.Ingredient;
import pl.dawidgdanski.bakery.library.model.IngredientImpl;

public class IngredientDeserializer extends AbstractDeserializer<Ingredient> {

    @Override
    public Ingredient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = (JsonObject) json;

        String id = getString(jsonObject.get(JSONConstants.JSON_ID), null);
        String name = getString(jsonObject.get(JSONConstants.JSON_NAME), null);

        JsonArray elementsArray = getJsonArray(jsonObject.get(JSONConstants.JSON_ELEMENTS));
        List<Element> elementList = new ArrayList<Element>(elementsArray.size());

        Type elementType = new TypeToken<Element>() { }.getType();

        for(JsonElement elementObject : elementsArray) {
            Element element = context.deserialize(elementObject, elementType);
            elementList.add(element);
        }

        return new IngredientImpl.Builder()
                .setId(id)
                .setName(name)
                .addElements(elementList)
                .build();
    }
}
