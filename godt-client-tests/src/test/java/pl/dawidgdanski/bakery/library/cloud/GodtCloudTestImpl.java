package pl.dawidgdanski.bakery.library.cloud;

import java.net.URL;

public class GodtCloudTestImpl extends GodtCloudImpl {

    private final String baseUrl;

    public GodtCloudTestImpl(URL url) {
        this.baseUrl = "http://" + url.getAuthority();
    }

    @Override
    protected String getEndpoint() {
        return baseUrl;
    }
}
