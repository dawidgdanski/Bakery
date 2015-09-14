package pl.dawidgdanski.bakery;

import com.squareup.otto.Bus;

import org.robolectric.RuntimeEnvironment;

import pl.dawidgdanski.bakery.database.DatabaseHelper;
import pl.dawidgdanski.bakery.inject.module.ApiModule;
import pl.dawidgdanski.bakery.inject.module.ApplicationModule;
import pl.dawidgdanski.bakery.inject.module.BusModule;
import pl.dawidgdanski.bakery.inject.module.CloudModule;
import pl.dawidgdanski.bakery.inject.module.DatabaseModule;
import pl.dawidgdanski.bakery.inject.module.TestApplicationModule;
import pl.dawidgdanski.bakery.inject.module.TestBusModule;
import pl.dawidgdanski.bakery.inject.module.TestCloudModule;
import pl.dawidgdanski.bakery.inject.module.TestDatabaseModule;
import pl.dawidgdanski.bakery.library.cloud.GodtCloud;

public class TestApp extends App {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public ApiModule getApiModule(App app) {
        return new ApiModule();
    }

    @Override
    public ApplicationModule getApplicationModule(App app) {
        return new TestApplicationModule(app);
    }

    @Override
    public BusModule getBusModule(App app) {
        return new TestBusModule();
    }

    @Override
    public CloudModule getCloudModule(App app) {
        return new TestCloudModule();
    }

    @Override
    public DatabaseModule getDatabaseModule(App app) {
        return new TestDatabaseModule();
    }

    public static TestApp get() {
        return  (TestApp)RuntimeEnvironment.application;
    }

    public Bus geInjectedBus() {
        return bus;
    }

    public DatabaseHelper getInjectedDatabaseHelper() {
        return databaseHelper;
    }

    public GodtCloud getInjectedGodtCloud() {
        return godtCloud;
    }
}
