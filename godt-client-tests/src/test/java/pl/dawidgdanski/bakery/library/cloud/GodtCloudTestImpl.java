package pl.dawidgdanski.bakery.library.cloud;

import java.net.URL;

import retrofit.client.OkClient;

public class GodtCloudTestImpl extends GodtCloudImpl {

    private final String baseUrl;

    public GodtCloudTestImpl(URL url) {
        super(new OkClient());
        this.baseUrl = "http://" + url.getAuthority();
    }

    @Override
    protected String getEndpoint() {
        return baseUrl;
    }
}
