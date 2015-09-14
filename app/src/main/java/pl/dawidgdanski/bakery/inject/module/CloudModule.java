package pl.dawidgdanski.bakery.inject.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.dawidgdanski.bakery.library.cloud.GodtCloud;
import pl.dawidgdanski.bakery.library.cloud.GodtCloudImpl;
import retrofit.client.Client;

@Module
public class CloudModule {

    @Provides
    @Singleton
    public GodtCloud provideGodtCloud(Client client) {
        return new GodtCloudImpl(client);
    }
}
