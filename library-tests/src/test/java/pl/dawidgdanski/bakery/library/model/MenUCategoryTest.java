package pl.dawidgdanski.bakery.library.model;

import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static pl.dawidgdanski.bakery.library.assertions.MenuCategoryAssert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP, manifest = Config.NONE)
public class MenuCategoryTest {

    @Test
    public void shouldParcelAndRestoreSelf() {
        MenuCategory menuCategory = new MenuCategoryImpl("George", "Smiley");

        assertThat(menuCategory).canBeParceled();
    }

}
