package pl.dawidgdanski.bakery.library.cloud;

import java.util.List;

import pl.dawidgdanski.bakery.library.model.Recipe;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

interface RecipesService {

    String RECIPES_URL = "/api/getRecipesListDetailed?tags=&size=thumbnail-medium&ratio=1";

    String URL_PARAMETER_FROM = "from";

    String URL_PARAMETER_LIMIT = "limit";

    @GET(RECIPES_URL)
    List<Recipe> getRecipes(
            @Query(URL_PARAMETER_FROM) int startIndex,
            @Query(URL_PARAMETER_LIMIT) int offset
    );

    @GET(RECIPES_URL)
    void getRecipes(
            @Query(URL_PARAMETER_FROM) int startIndex,
            @Query(URL_PARAMETER_LIMIT) int offset,
            Callback<List<Recipe>> recipes
    );

}
