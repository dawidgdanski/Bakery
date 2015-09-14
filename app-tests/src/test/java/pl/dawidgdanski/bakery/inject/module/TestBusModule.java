package pl.dawidgdanski.bakery.inject.module;

import com.squareup.otto.Bus;

import static org.mockito.Mockito.spy;

public class TestBusModule extends BusModule {

    @Override
    public Bus provideBus() {
        return spy(super.provideBus());
    }
}
