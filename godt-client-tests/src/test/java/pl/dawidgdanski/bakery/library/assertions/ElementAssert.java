package pl.dawidgdanski.bakery.library.assertions;

import android.os.Parcelable;

import pl.dawidgdanski.bakery.library.model.Element;
import pl.dawidgdanski.bakery.library.model.ElementImpl;
import pl.dawidgdanski.bakery.library.model.IngredientImpl;

public class ElementAssert extends AbstractTestAssert<ElementAssert, Element> {

    public static ElementAssert assertThat(final Element element) {
        return new ElementAssert(element);
    }

    private ElementAssert(Element actual) {
        super(actual, ElementAssert.class);
    }

    @Override
    protected Parcelable.Creator getCreator() {
        return ElementImpl.CREATOR;
    }
}
