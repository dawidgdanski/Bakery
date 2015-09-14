package pl.dawidgdanski.bakery.inject.module;

import com.squareup.otto.Bus;

public class TestBusModule extends BusModule {

    @Override
    public Bus provideBus() {
        return super.provideBus();
    }
}
