package pl.dawidgdanski.bakery.library.assertions;

import android.os.Parcelable;

import pl.dawidgdanski.bakery.library.model.Ingredient;
import pl.dawidgdanski.bakery.library.model.IngredientImpl;

public class IngredientAssert extends AbstractTestAssert<IngredientAssert, Ingredient> {

    public static IngredientAssert assertThat(final Ingredient ingredient) {
        return new IngredientAssert(ingredient);
    }

    private IngredientAssert(Ingredient actual) {
        super(actual, IngredientAssert.class);
    }

    @Override
    protected Parcelable.Creator getCreator() {
        return IngredientImpl.CREATOR;
    }
}
