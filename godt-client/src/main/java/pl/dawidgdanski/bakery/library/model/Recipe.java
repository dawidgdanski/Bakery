package pl.dawidgdanski.bakery.library.model;

import android.os.Parcelable;

import java.util.Collection;

public interface Recipe extends Parcelable {

    String getId();

    String getTitle();

    String getDescription();

    Collection<Ingredient> getIngredients();
}
