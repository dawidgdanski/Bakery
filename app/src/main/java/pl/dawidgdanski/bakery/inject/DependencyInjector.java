package pl.dawidgdanski.bakery.inject;

import com.google.common.base.Preconditions;

import pl.dawidgdanski.bakery.App;

public class DependencyInjector {

    private static DependencyGraph dependencyGraph;

    public static synchronized void initialize(final App app,
                                               final ModuleProvisionContract moduleProvisionContract) {
        dependencyGraph = MainComponent.Initializer.initialize(app, moduleProvisionContract);
    }

    public static synchronized boolean isInitialized() {
        return dependencyGraph != null;
    }

    public static synchronized DependencyGraph getGraph() {
        Preconditions.checkNotNull(isInitialized(), "Dependency Graph not initialized");

        return dependencyGraph;
    }

    protected DependencyInjector() {

    }

}
