package pl.dawidgdanski.bakery.inject.module;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import retrofit.client.Client;
import retrofit.client.OkClient;

@Module
public class ApiModule {

    @Provides
    Client provideApiClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);

        return new OkClient(okHttpClient);
    }

}
