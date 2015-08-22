package pl.dawidgdanski.bakery.library.cloud;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import pl.dawidgdanski.bakery.library.model.Element;
import pl.dawidgdanski.bakery.library.model.ElementImpl;
import pl.dawidgdanski.bakery.library.model.MenuCategory;
import pl.dawidgdanski.bakery.library.model.MenuCategoryImpl;

final class ElementDeserializer extends AbstractDeserializer<Element> {

    @Override
    public Element deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) json;

        JsonElement idElement = jsonObject.get(JSONConstants.JSON_ID);
        String id = getString(idElement, null);

        JsonElement amountElement = jsonObject.get(JSONConstants.JSON_AMOUNT);
        int amount = getInt(amountElement, 0);

        String hint = getString(jsonObject.get(JSONConstants.JSON_NAME), null);
        String unitName = getString(jsonObject.get(JSONConstants.JSON_SYMBOL), null);

        JsonObject menuCategoryObject = getJsonObject(jsonObject.get(JSONConstants.JSON_MENU_CATEGORY));

        JsonElement menuCategoryIdElement = menuCategoryObject.get(JSONConstants.JSON_ID);

        String menuCategoryId = getString(menuCategoryIdElement, null);

        JsonElement menuCategoryNameElement = menuCategoryObject.get(JSONConstants.JSON_NAME);

        String name = getString(menuCategoryNameElement, null);

        MenuCategory menuCategory = new MenuCategoryImpl(name, menuCategoryId);

        return new ElementImpl.Builder()
                .setMenuCategory(menuCategory)
                .setUnitName(unitName)
                .setHint(hint)
                .setAmount(amount)
                .setId(id)
                .build();
    }
}
