package pl.dawidgdanski.bakery.library.cloud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import pl.dawidgdanski.bakery.library.cloud.json.ElementDeserializer;
import pl.dawidgdanski.bakery.library.cloud.json.IngredientDeserializer;
import pl.dawidgdanski.bakery.library.cloud.json.RecipeDeserializer;
import pl.dawidgdanski.bakery.library.model.Element;
import pl.dawidgdanski.bakery.library.model.Ingredient;
import pl.dawidgdanski.bakery.library.model.Recipe;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

public class GodtCloudImpl implements GodtCloud {

    private static final String BAKERY_RECIPES_URL = "http://www.godt.no";

    private final RecipesService recipesService;

    public GodtCloudImpl() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Recipe.class, new RecipeDeserializer())
                .registerTypeAdapter(Ingredient.class, new IngredientDeserializer())
                .registerTypeAdapter(Element.class, new ElementDeserializer())
                .create();

        Converter converter = new GsonConverter(gson);

        OkClient client = new OkClient(new OkHttpClient());

        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BAKERY_RECIPES_URL)
                .setClient(client)
                .setConverter(converter)
                .build();

        recipesService = restAdapter.create(RecipesService.class);
    }

    @Override
    public List<Recipe> getRecipesPage(int startIndex, int offset) {
        return recipesService.getRecipes(startIndex, offset);
    }

    @Override
    public void getRecipesPage(int startIndex, int offset, Callback<List<Recipe>> callback) {
        recipesService.getRecipes(startIndex, offset, callback);
    }
}
