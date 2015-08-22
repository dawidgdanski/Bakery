package pl.dawidgdanski.bakery.library.model;

import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static pl.dawidgdanski.bakery.library.assertions.IngredientAssert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP, manifest = Config.NONE)
public class IngredientTest {

    @Test
    public void shouldParcelAndRestoreSelf() {
        Ingredient SUT = new IngredientImpl.Builder()
                .setAmount(34)
                .setId("_1")
                .setHint("Mole")
                .setName("Karla")
                .setMenuCategory(new MenuCategoryImpl("Percy", "Alleline"))
                .setSymbol("mi6")
                .setUnitName("Circus")
                .build();

        assertThat(SUT).canBeParceled();
    }
}
