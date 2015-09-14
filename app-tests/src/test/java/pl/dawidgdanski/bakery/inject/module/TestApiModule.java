package pl.dawidgdanski.bakery.inject.module;

import retrofit.client.Client;
import retrofit.client.OkClient;

public class TestApiModule extends ApiModule {

    @Override
    Client provideApiClient() {
        return new OkClient();
    }
}
