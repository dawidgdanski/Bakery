package pl.dawidgdanski.bakery.library.cloud;

import java.util.List;

import pl.dawidgdanski.bakery.library.model.Recipe;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

interface RecipesService {

    @GET("/api/getRecipesListDetailed?tags=&size=thumbnail-medium&ratio=1")
    List<Recipe> getRecipes(
            @Query("from") int startIndex,
            @Query("limit") int offset
    );

    @GET("/api/getRecipesListDetailed?tags=&size=thumbnail-medium&ratio=1")
    void getRecipes(
            @Query("from") int startIndex,
            @Query("limit") int offset,
            Callback<List<Recipe>> recipes
    );

}
