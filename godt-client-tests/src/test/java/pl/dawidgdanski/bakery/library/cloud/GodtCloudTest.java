package pl.dawidgdanski.bakery.library.cloud;

import android.os.Build;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.rule.MockWebServerRule;

import org.fest.assertions.Assertions;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.List;

import pl.dawidgdanski.bakery.library.TestDataProvider;
import pl.dawidgdanski.bakery.library.model.Recipe;
import retrofit.client.OkClient;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP, manifest = Config.NONE)
public class GodtCloudTest {

    @Rule
    public final MockWebServerRule mockWebServerRule = new MockWebServerRule();

    @Test
    public void godtCloudTest() throws IOException {

        mockWebServerRule.enqueue(new MockResponse().setBody(TestDataProvider.generate10RecipesPageJson()));

        final int startIndex = 0;

        final int offset = 10;

        final String parameters = String.format("&%s=%d&%s=%d",
                RecipesService.URL_PARAMETER_FROM,
                startIndex,
                RecipesService.URL_PARAMETER_LIMIT,
                offset);

        GodtCloud cloud = new GodtCloudTestImpl(mockWebServerRule.getUrl(RecipesService.RECIPES_URL + parameters));
        List<Recipe> recipeList = cloud.getRecipesPage(startIndex, offset);

        Assertions.assertThat(mockWebServerRule.getRequestCount()).isEqualTo(1);
    }

    @Ignore
    @Test
    public void productionGodtCloudTest() {
        GodtCloud cloud = new GodtCloudImpl(new OkClient());

        List<Recipe> recipeList = cloud.getRecipesPage(0, 10);

        int size = recipeList.size();
    }

}
