package pl.dawidgdanski.bakery.library.model;

import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static pl.dawidgdanski.bakery.library.assertions.StepAssert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP, manifest = Config.NONE)
public class StepTest {

    @Test
    public void shouldParcelAndRestoreSelf() {
        Step SUT = new StepImpl.Builder()
                .setId("id")
                .setDescription("Description")
                .setHeading("Heading")
                .build();

        assertThat(SUT).canBeParceled();
    }

}
