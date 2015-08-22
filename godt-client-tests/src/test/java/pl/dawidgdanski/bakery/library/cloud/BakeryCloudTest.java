package pl.dawidgdanski.bakery.library.cloud;

import android.os.Build;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import pl.dawidgdanski.bakery.library.model.Recipe;

@Ignore
@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP, manifest = Config.NONE)
public class BakeryCloudTest {

    @Test
    public void bakeryCloudTest() {
        BakeryCloud cloud = new BakeryCloudImpl();
        List<Recipe> recipeList = cloud.getRecipesPage(0, 50);

        int size = recipeList.size();
    }

}
