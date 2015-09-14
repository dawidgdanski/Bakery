package pl.dawidgdanski.bakery.inject;

import pl.dawidgdanski.bakery.App;
import pl.dawidgdanski.bakery.inject.module.ApiModule;
import pl.dawidgdanski.bakery.inject.module.ApplicationModule;
import pl.dawidgdanski.bakery.inject.module.BusModule;
import pl.dawidgdanski.bakery.inject.module.CloudModule;
import pl.dawidgdanski.bakery.inject.module.DatabaseModule;

public interface ModuleProvisionContract {

    ApiModule getApiModule(App app);

    ApplicationModule getApplicationModule(App app);

    BusModule getBusModule(App app);

    CloudModule getCloudModule(App app);

    DatabaseModule getDatabaseModule(App app);
}
