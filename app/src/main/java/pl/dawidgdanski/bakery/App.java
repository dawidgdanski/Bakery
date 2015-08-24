package pl.dawidgdanski.bakery;

import android.app.Application;
import android.content.Context;

import com.google.common.base.Preconditions;
import com.squareup.otto.Bus;

import pl.dawidgdanski.bakery.controller.AppController;
import pl.dawidgdanski.bakery.database.DatabaseHelper;
import pl.dawidgdanski.bakery.library.cloud.GodtCloud;
import pl.dawidgdanski.bakery.library.cloud.GodtCloudImpl;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.initialize(this);
        AppController.initialize(this);
    }
}
