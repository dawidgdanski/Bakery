package pl.dawidgdanski.bakery;

import android.app.Application;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;

import pl.dawidgdanski.bakery.database.DatabaseHelper;

@RunWith(AppRobolectricTestRunner.class)
public class AndroidManifestTest {

    @Test
    public void shouldLaunchTestApp() {
        final Application application = RuntimeEnvironment.application;

        Assertions.assertThat(application).isInstanceOf(TestApp.class);

        Assertions.assertThat(DatabaseHelper.isInitialized()).isTrue();
    }

}
