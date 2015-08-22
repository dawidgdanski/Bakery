package pl.dawidgdanski.bakery.library.model;

import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import pl.dawidgdanski.bakery.library.TestDataProvider;

import static pl.dawidgdanski.bakery.library.assertions.RecipeAssert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP, manifest = Config.NONE)
public class RecipeTest {

    @Test
    public void shouldParcelAndRestoreSelf() {

        final Recipe recipe = new RecipeImpl.Builder()
                .setDescription("TinkerTailor")
                .setTitle("SoldierSailor")
                .setImageUrl("http:url.jpg")
                .addIngredients(TestDataProvider.generateNextIngredients(100))
                .build();

        assertThat(recipe).canBeParceled();
    }



}
