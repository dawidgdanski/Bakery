package pl.dawidgdanski.bakery.library.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

public interface Element extends Parcelable {

    @Nullable
    String getId();

    String getName();

    int getAmount();

    @Nullable
    String getHint();

    String getUnitName();

    String getSymbol();
}
