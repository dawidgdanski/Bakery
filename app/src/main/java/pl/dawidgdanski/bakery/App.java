package pl.dawidgdanski.bakery;

import android.app.Application;

import pl.dawidgdanski.bakery.provider.DatabaseHelper;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.init(this);
    }
}
