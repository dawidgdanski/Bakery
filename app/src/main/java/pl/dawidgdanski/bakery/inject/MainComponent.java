package pl.dawidgdanski.bakery.inject;

import pl.dawidgdanski.bakery.App;

interface MainComponent extends DependencyGraph {

    final class Initializer {
        private Initializer() {
        }

        static DependencyGraph initialize(App app, ModuleProvisionContract moduleProvisionContract) {
            return DaggerDependencyGraph.builder()
                    .applicationModule(moduleProvisionContract.getApplicationModule(app))
                    .apiModule(moduleProvisionContract.getApiModule(app))
                    .busModule(moduleProvisionContract.getBusModule(app))
                    .cloudModule(moduleProvisionContract.getCloudModule(app))
                    .databaseModule(moduleProvisionContract.getDatabaseModule(app))
                    .build();
        }
    }

}
