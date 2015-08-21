package pl.dawidgdanski.bakery.assertions;

import android.os.Parcel;
import android.os.Parcelable;

import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;

public abstract class AbstractTestAssert<S extends AbstractAssert<S, A>, A> extends AbstractAssert<S, A> {

    protected AbstractTestAssert(A actual, Class<?> selfType) {
        super(actual, selfType);
        isNotNull();
    }

    protected abstract Parcelable.Creator getCreator();

    public final S canBeParceled() {
        Assertions.assertThat(actual).overridingErrorMessage(String.format("Class %s does not implement Parcelable interface", actual.getClass().getSimpleName()))
                .isInstanceOf(Parcelable.class);
        final Parcelable parcelable = (Parcelable) actual;
        final Parcel parcel = Parcel.obtain();
        parcelable.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        A retrievedInstance = (A) getCreator().createFromParcel(parcel);
        Assertions.assertThat(retrievedInstance).isNotNull()
                .isEqualTo(actual);
        return myself;
    }
}
