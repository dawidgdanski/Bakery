package pl.dawidgdanski.bakery.library.cloud;

import android.os.Build;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.fest.assertions.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.List;

import pl.dawidgdanski.bakery.library.TestDataProvider;
import pl.dawidgdanski.bakery.library.model.Recipe;

@Ignore
@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP, manifest = Config.NONE)
public class GodtCloudTest {

    private MockWebServer mockWebServer;

    @Before
    public void setUp() {
        mockWebServer = new MockWebServer();
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }


    @Test
    public void bakeryCloudTest() throws IOException {

        mockWebServer.enqueue(new MockResponse().setBody(TestDataProvider.generate10RecipesPageJson()));

        mockWebServer.start();

        GodtCloud cloud = new GodtCloudTestImpl(mockWebServer.getUrl("/").toString());
        List<Recipe> recipeList = cloud.getRecipesPage(0, 10);

        Assertions.assertThat(mockWebServer.getRequestCount()).isEqualTo(1);
    }

}
