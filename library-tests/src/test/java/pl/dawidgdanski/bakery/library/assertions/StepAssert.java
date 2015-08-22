package pl.dawidgdanski.bakery.library.assertions;

import android.os.Parcelable;

import pl.dawidgdanski.bakery.library.model.Step;
import pl.dawidgdanski.bakery.library.model.StepImpl;

public class StepAssert extends AbstractTestAssert<StepAssert, Step> {

    public static StepAssert assertThat(final Step step) {
        return new StepAssert(step);
    }

    private StepAssert(Step actual) {
        super(actual, StepAssert.class);
    }

    @Override
    protected Parcelable.Creator getCreator() {
        return StepImpl.CREATOR;
    }
}
