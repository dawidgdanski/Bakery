package pl.dawidgdanski.bakery.controller;

import com.squareup.otto.Bus;

public final class BusProvider {

    private BusProvider() {
    }

    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }
}
