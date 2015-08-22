package pl.dawidgdanski.bakery.library.model;

import android.os.Parcelable;

import java.util.Collection;

public interface Recipe extends Parcelable {

    String getTitle();

    String getDescription();

    Collection<Ingredient> getIngredients();

    Collection<Step> getSteps();
}