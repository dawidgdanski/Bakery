package pl.dawidgdanski.bakery.library.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.Collection;

public interface Ingredient extends Parcelable {

    @Nullable
    String getId();

    String getName();

    Collection<Element> getElements();
}
