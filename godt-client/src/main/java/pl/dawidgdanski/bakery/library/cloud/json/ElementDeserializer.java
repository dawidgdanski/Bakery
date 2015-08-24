package pl.dawidgdanski.bakery.library.cloud.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import pl.dawidgdanski.bakery.library.model.Element;
import pl.dawidgdanski.bakery.library.model.ElementImpl;

public final class ElementDeserializer extends AbstractDeserializer<Element> {

    @Override
    public Element deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) json;

        JsonElement idElement = jsonObject.get(JSONConstants.JSON_ID);
        String id = getString(idElement, null);

        JsonElement amountElement = jsonObject.get(JSONConstants.JSON_AMOUNT);
        int amount = getInt(amountElement, 0);

        String name = getString(jsonObject.get(JSONConstants.JSON_NAME), null);
        String unitName = getString(jsonObject.get(JSONConstants.JSON_UNIT_NAME), null);

        String symbol = getString(jsonObject.get(JSONConstants.JSON_SYMBOL), null);

        String hint = getString(jsonObject.get(JSONConstants.JSON_HINT), null);

        return new ElementImpl.Builder()
                .setUnitName(unitName)
                .setSymbol(symbol)
                .setName(name)
                .setAmount(amount)
                .setHint(hint)
                .setId(id)
                .build();
    }
}
