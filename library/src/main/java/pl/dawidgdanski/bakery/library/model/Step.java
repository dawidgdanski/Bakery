package pl.dawidgdanski.bakery.library.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

public interface Step extends Parcelable {

    String getId();

    @Nullable
    String getHeading();

    String getDescription();
}
