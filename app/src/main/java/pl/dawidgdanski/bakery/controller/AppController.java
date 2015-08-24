package pl.dawidgdanski.bakery.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.common.base.Preconditions;


public class AppController  {

    public static final int RECIPES_OFFSET = 10;

    private static final int MAX_RECIPES_LOADED = 50;

    private static AppController SINGLETON;

    public static void initialize(final Context context) {
        Preconditions.checkNotNull(context);
        SINGLETON = new AppController(context.getApplicationContext());
    }

    public static synchronized AppController getInstance() {
        Preconditions.checkNotNull(SINGLETON);

        return SINGLETON;
    }

    private static final String PREFERENCE_LOADED_RECIPES = "pref_loaded_recipes";

    private final Preferences preferences;

    private AppController(final Context context) {
        this.preferences = new Preferences("app_settings", context);
    }

    public int getLoadedRecipesCount() {
        return preferences.getInt(PREFERENCE_LOADED_RECIPES, 0);
    }

    public void setLoadedRecipesCount(final int count) {
        preferences.putInt(PREFERENCE_LOADED_RECIPES, count);
    }

    public boolean isDeviceOnline(final Context context) {

        if(context == null) {
            return true;
        }

        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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
