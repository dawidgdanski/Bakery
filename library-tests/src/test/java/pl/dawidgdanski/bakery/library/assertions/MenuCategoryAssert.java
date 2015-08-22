package pl.dawidgdanski.bakery.library.assertions;

import android.os.Parcelable;

import pl.dawidgdanski.bakery.library.model.MenuCategory;
import pl.dawidgdanski.bakery.library.model.MenuCategoryImpl;

public class MenuCategoryAssert extends AbstractTestAssert<MenuCategoryAssert, MenuCategory> {

    public static MenuCategoryAssert assertThat(final MenuCategory actual) {
        return new MenuCategoryAssert(actual);
    }

    private MenuCategoryAssert(MenuCategory actual) {
        super(actual, MenuCategoryAssert.class);
    }

    @Override
    protected Parcelable.Creator getCreator() {
        return MenuCategoryImpl.CREATOR;
    }
}
