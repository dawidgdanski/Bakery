package pl.dawidgdanski.bakery.inject.module;

import retrofit.client.Client;
import retrofit.client.OkClient;

import static org.mockito.Mockito.spy;

public class TestApiModule extends ApiModule {

    @Override
    Client provideApiClient() {
        return spy(new OkClient());
    }
}
