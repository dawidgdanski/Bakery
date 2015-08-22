package pl.dawidgdanski.bakery.library.cloud.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

abstract class AbstractDeserializer<T> implements JsonDeserializer<T> {

    private static final JsonArray EMPTY_ARRAY = new JsonArray();

    private static final JsonObject EMPTY_OBJECT = new JsonObject();

    protected String getString(final JsonElement element, final String defaultValue) {
        return (element == null || element.isJsonNull()) ? defaultValue : element.getAsString();
    }

    protected int getInt(final JsonElement element, final int defaultValue) {
        return (element == null || element.isJsonNull()) ? defaultValue : element.getAsInt();
    }

    protected JsonArray getJsonArray(final JsonElement element) {
        return (element == null || element.isJsonNull()) ? EMPTY_ARRAY : element.getAsJsonArray();
    }

    protected JsonObject getJsonObject(final JsonElement element) {
        return (element == null || element.isJsonNull()) ? EMPTY_OBJECT : element.getAsJsonObject();
    }

}
