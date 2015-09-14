package pl.dawidgdanski.bakery.inject.module;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;

import pl.dawidgdanski.bakery.App;
import pl.dawidgdanski.bakery.controller.AppController;

import static org.mockito.Mockito.spy;

public class TestApplicationModule extends ApplicationModule {

    public TestApplicationModule(App app) {
        super(app);
    }

    @Override
    public App provideApplication() {
        return spy(super.provideApplication());
    }

    @Override
    public Resources provideResources() {
        return spy(super.provideResources());
    }

    @Override
    public Context provideApplicationContext() {
        return spy(super.provideApplicationContext());
    }

    @Override
    public AppController provideAppController(App app) {
        return spy(super.provideAppController(app));
    }

    @Override
    public ContentResolver provideContentResolver() {
        return spy(super.provideContentResolver());
    }
}
