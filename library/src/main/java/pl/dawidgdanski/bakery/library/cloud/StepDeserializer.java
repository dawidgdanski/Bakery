package pl.dawidgdanski.bakery.library.cloud;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import pl.dawidgdanski.bakery.library.model.Step;
import pl.dawidgdanski.bakery.library.model.StepImpl;

final class StepDeserializer extends AbstractDeserializer<Step> {
    @Override
    public Step deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) json;

        String id = getString(jsonObject.get(JSONConstants.JSON_ID), null);
        String heading = getString(jsonObject.get(JSONConstants.JSON_HEADING), null);
        String description = getString(jsonObject.get(JSONConstants.JSON_DESCRIPTION), null);

        return new StepImpl.Builder()
                .setDescription(description)
                .setHeading(heading)
                .setId(id)
                .build();
    }
}
