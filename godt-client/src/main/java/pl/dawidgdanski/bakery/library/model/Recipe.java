package pl.dawidgdanski.bakery.library.model;

import java.util.Collection;

public interface Recipe extends Model {

    String getId();

    String getTitle();

    String getDescription();

    String getImageUrl();

    Collection<Ingredient> getIngredients();
}
