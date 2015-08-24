package pl.dawidgdanski.bakery.controller;

import pl.dawidgdanski.bakery.library.cloud.GodtCloud;
import pl.dawidgdanski.bakery.library.cloud.GodtCloudImpl;

public class GodtCloudProvider {

    private GodtCloudProvider() { }

    private static final GodtCloud CLOUD = new GodtCloudImpl();

    public static GodtCloud getInstance() {
        return CLOUD;
    }
}
