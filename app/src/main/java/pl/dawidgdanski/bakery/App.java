package pl.dawidgdanski.bakery;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import pl.dawidgdanski.bakery.database.DatabaseHelper;
import pl.dawidgdanski.bakery.inject.DependencyInjector;
import pl.dawidgdanski.bakery.inject.ModuleProvisionContract;
import pl.dawidgdanski.bakery.inject.module.ApiModule;
import pl.dawidgdanski.bakery.inject.module.ApplicationModule;
import pl.dawidgdanski.bakery.inject.module.BusModule;
import pl.dawidgdanski.bakery.inject.module.CloudModule;
import pl.dawidgdanski.bakery.inject.module.DatabaseModule;
import pl.dawidgdanski.bakery.library.cloud.GodtCloud;

public class App extends Application implements ModuleProvisionContract {

    @Inject
    Bus bus;

    @Inject
    GodtCloud godtCloud;

    @Inject
    DatabaseHelper databaseHelper;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        DependencyInjector.initialize(this, this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DependencyInjector.getGraph().inject(this);
    }

    @Override
    public ApiModule getApiModule(App app) {
        return new ApiModule();
    }

    @Override
    public ApplicationModule getApplicationModule(App app) {
        return new ApplicationModule(app);
    }

    @Override
    public BusModule getBusModule(App app) {
        return new BusModule();
    }

    @Override
    public CloudModule getCloudModule(App app) {
        return new CloudModule();
    }

    @Override
    public DatabaseModule getDatabaseModule(App app) {
        return new DatabaseModule();
    }
}
