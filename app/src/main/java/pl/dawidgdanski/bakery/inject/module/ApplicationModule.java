package pl.dawidgdanski.bakery.inject.module;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.dawidgdanski.bakery.App;
import pl.dawidgdanski.bakery.controller.AppController;

@Module
public class ApplicationModule {

    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public App provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    public Resources provideResources() {
        return app.getResources();
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    public AppController provideAppController(App app) {
        return new AppController(app);
    }

    @Provides
    @Singleton
    public ContentResolver provideContentResolver() {
        return app.getContentResolver();
    }

}
