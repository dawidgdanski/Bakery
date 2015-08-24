package pl.dawidgdanski.bakery.library.cloud.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.reflect.Type;
import java.util.List;

import pl.dawidgdanski.bakery.library.TestDataProvider;
import pl.dawidgdanski.bakery.library.model.Element;
import pl.dawidgdanski.bakery.library.model.Ingredient;
import pl.dawidgdanski.bakery.library.model.Recipe;

@RunWith(RobolectricTestRunner.class)
public class JsonSchemaTest {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Recipe.class, new RecipeDeserializer())
            .registerTypeAdapter(Ingredient.class, new IngredientDeserializer())
            .registerTypeAdapter(Element.class, new ElementDeserializer())
            .create();

    @Test
    public void singleJsonSchemaTest() {
        Type type = new TypeToken<List<Recipe>>(){}.getType();
        List<Recipe> recipes = gson.fromJson(TestDataProvider.getSingleJsonSchema(), type);

        Assertions.assertThat(recipes).isNotNull();

        Recipe recipe = recipes.get(0);

        Assertions.assertThat(recipe.getTitle()).isEqualTo("recipe_title");
        Assertions.assertThat(recipe.getDescription()).isEqualTo("recipe_description");
        Assertions.assertThat(recipe.getImageUrl()).isEqualTo("image_url");

        for(Ingredient ingredient : recipe.getIngredients()) {

            Assertions.assertThat(ingredient.getName()).isEqualTo("ingredient_name");
            Assertions.assertThat(ingredient.getId()).isEqualTo("ingredient_id");

            for(Element element : ingredient.getElements()) {
                Assertions.assertThat(element.getId()).isEqualTo("1213");
                Assertions.assertThat(element.getAmount()).isEqualTo(4);
                Assertions.assertThat(element.getUnitName()).isEqualTo("element_unit_name");
                Assertions.assertThat(element.getName()).isEqualTo("element_name");
                Assertions.assertThat(element.getHint()).isEqualTo("element_hint");
                Assertions.assertThat(element.getSymbol()).isEqualTo("element_symbol");
            }
        }
    }

}
