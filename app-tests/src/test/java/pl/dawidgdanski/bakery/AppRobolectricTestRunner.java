package pl.dawidgdanski.bakery;

import android.os.Build;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.Fs;

public class AppRobolectricTestRunner extends RobolectricGradleTestRunner {

    private static final int TARGET_SDK_VERSION = Build.VERSION_CODES.LOLLIPOP;
    private static final int MIN_SDK_VERSION = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;

    public AppRobolectricTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {

        final String projectDirectory = System.getProperty("user.dir");

        final String manifestFile = "/app-tests/src/test/resources/AndroidManifest.xml";
        final String resProperty = "/app/src/main/res";
        final String assetsProperty = "/app/src/main/assets";

        AndroidManifest manifest = new AndroidManifest(Fs.fileFromPath(projectDirectory + manifestFile),
                Fs.fileFromPath(projectDirectory + resProperty),
                Fs.fileFromPath(projectDirectory + assetsProperty)) {
            @Override
            public int getTargetSdkVersion() {
                return TARGET_SDK_VERSION;
            }

            @Override
            public int getMinSdkVersion() {
                return MIN_SDK_VERSION;
            }
        };

        manifest.setPackageName("pl.dawidgdanski.bakery");

        return manifest;
    }


}
