package pl.dawidgdanski.bakery.library.model;

import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import pl.dawidgdanski.bakery.library.TestDataProvider;

import static pl.dawidgdanski.bakery.library.assertions.IngredientAssert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP, manifest = Config.NONE)
public class IngredientTest {

    @Test
    public void shouldParcelAndRestoreSelf() {
        Ingredient SUT = new IngredientImpl.Builder()
                .setId(":ID")
                .setName("asdf")
                .addElements(TestDataProvider.generateNextElements(100))
                .build();

        assertThat(SUT).canBeParceled();
    }

}
