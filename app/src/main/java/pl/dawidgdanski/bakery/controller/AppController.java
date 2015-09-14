package pl.dawidgdanski.bakery.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import pl.dawidgdanski.bakery.App;

public class AppController  {

    public static final String PREFERENCES_NAME = "app_settings";

    public static final int RECIPES_OFFSET = 10;

    private static final int MAX_RECIPES_LOADED = 50;

    private static final String PREFERENCE_LOADED_RECIPES = "pref_loaded_recipes";

    private final Preferences preferences;

    private final ConnectivityManager connectivityManager;

    public AppController(App app) {
        this.preferences = new Preferences(PREFERENCES_NAME, app);
        this.connectivityManager = (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public int getLoadedRecipesCount() {
        return preferences.getInt(PREFERENCE_LOADED_RECIPES, 0);
    }

    public void setLoadedRecipesCount(final int count) {
        preferences.putInt(PREFERENCE_LOADED_RECIPES, count);
    }

    public boolean isDeviceOnline() {

        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public boolean areAllRecipesLoaded() {
        return getLoadedRecipesCount() >= MAX_RECIPES_LOADED;
    }

    public void clearData() {
        preferences.clear();
    }
}
