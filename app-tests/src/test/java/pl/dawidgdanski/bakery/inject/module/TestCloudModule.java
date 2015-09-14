package pl.dawidgdanski.bakery.inject.module;

import pl.dawidgdanski.bakery.library.cloud.GodtCloud;
import retrofit.client.Client;

import static org.mockito.Mockito.spy;

public class TestCloudModule extends CloudModule {
    @Override
    public GodtCloud provideGodtCloud(Client client) {
        return spy(super.provideGodtCloud(client));
    }
}
