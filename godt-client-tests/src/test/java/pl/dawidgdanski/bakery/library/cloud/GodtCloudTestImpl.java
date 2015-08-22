package pl.dawidgdanski.bakery.library.cloud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import pl.dawidgdanski.bakery.library.cloud.json.ElementDeserializer;
import pl.dawidgdanski.bakery.library.cloud.json.IngredientDeserializer;
import pl.dawidgdanski.bakery.library.model.Element;
import pl.dawidgdanski.bakery.library.model.Ingredient;
import pl.dawidgdanski.bakery.library.model.Recipe;
import retrofit.Callback;

public class GodtCloudTestImpl implements GodtCloud {

    private final OkHttpClient okHttpClient = new OkHttpClient();

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Recipe.class, new RecipeDeserializer())
            .registerTypeAdapter(Ingredient.class, new IngredientDeserializer())
            .registerTypeAdapter(Element.class, new ElementDeserializer())
            .create();

    private final String url;

    public GodtCloudTestImpl(final String url) {
        this.url = url;
    }

    @Override
    public List<Recipe> getRecipesPage(int startIndex, int offset) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();

            Type type = new TypeToken<List<Recipe>>(){}.getType();
            return gson.fromJson(response.body().string(), type);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void getRecipesPage(int startIndex, int offset, Callback<List<Recipe>> callback) {
        throw new UnsupportedOperationException();
    }
}
