package pl.dawidgdanski.bakery.provider;

import android.content.ContentProviderClient;
import android.content.ContentResolver;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;

import pl.dawidgdanski.bakery.AppRobolectricTestRunner;

@RunWith(AppRobolectricTestRunner.class)
public class AppContentProviderTest {

    @Test
    public void shouldAcquireContentProviderClientForAuthority() {

        ContentResolver contentResolver = RuntimeEnvironment.application.getContentResolver();

        ContentProviderClient client = contentResolver.acquireContentProviderClient(AppContentProvider.AUTHORITY);

        Assertions.assertThat(client).isNotNull();
    }

}
