package pl.dawidgdanski.bakery.library.cloud;

import java.util.List;

import pl.dawidgdanski.bakery.library.model.Recipe;
import retrofit.Callback;

public interface GodtCloud {

    List<Recipe> getRecipesPage(int startIndex, int offset);

    void getRecipesPage(int startIndex, int offset, Callback<List<Recipe>> callback);

}
