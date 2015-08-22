package pl.dawidgdanski.bakery.library.assertions;

import android.os.Parcelable;

import pl.dawidgdanski.bakery.library.model.Recipe;
import pl.dawidgdanski.bakery.library.model.RecipeImpl;

public class RecipeAssert extends AbstractTestAssert<RecipeAssert, Recipe> {

    public static RecipeAssert assertThat(final Recipe actual) {
        return new RecipeAssert(actual);
    }

    private RecipeAssert(Recipe actual) {
        super(actual, RecipeAssert.class);
    }

    @Override
    protected Parcelable.Creator getCreator() {
        return RecipeImpl.CREATOR;
    }
}
