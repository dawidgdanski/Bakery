package pl.dawidgdanski.bakery.controller;

import android.content.Context;


public class AppController  {

    private static AppController SINGLETON;

    public static synchronized AppController getInstance(final Context context) {
        if(SINGLETON == null) {
            SINGLETON = new AppController(context.getApplicationContext());
        }

        return SINGLETON;
    }

    private static final String PREFERENCE_LOADED_RECIPES = "pref_loaded_recipes";

    private static final String PREFERENCES_ALL_RECIPES_LOADED = "pref_all_recipes";

    private final Preferences preferences;

    private AppController(final Context context) {
        this.preferences = new Preferences("app_settings", context);
    }

    public int getLoadedRecipesCount() {
        return preferences.getInt(PREFERENCE_LOADED_RECIPES);
    }

    public void setRecipesCount(final int count) {
        preferences.putInt(PREFERENCE_LOADED_RECIPES, count);
    }

    public boolean areAllRecipesLoaded() {
        return preferences.getBoolean(PREFERENCES_ALL_RECIPES_LOADED);
    }

    public void setAllRecipesLoaded(boolean state) {
        preferences.putBoolean(PREFERENCES_ALL_RECIPES_LOADED, state);
    }

    public void clearData() {
        preferences.clear();
    }

}
