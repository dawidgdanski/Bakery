package pl.dawidgdanski.bakery.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.List;

import pl.dawidgdanski.bakery.controller.AppController;
import pl.dawidgdanski.bakery.controller.BusProvider;
import pl.dawidgdanski.bakery.controller.GodtCloudProvider;
import pl.dawidgdanski.bakery.controller.PersistenceManager;
import pl.dawidgdanski.bakery.event.RecipesLoadedEvent;
import pl.dawidgdanski.bakery.library.model.Recipe;

public class RecipesDownloadService extends IntentService {

    private static final String TAG = RecipesDownloadService.class.getSimpleName();

    private PersistenceManager persistenceManager;

    private MainThreadHandler mainThreadHandler;

    public RecipesDownloadService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        persistenceManager = new PersistenceManager(this);
        mainThreadHandler = new MainThreadHandler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        AppController appController = AppController.getInstance();

        final int loadedRecipes = appController.getLoadedRecipesCount();
        final int offset = AppController.RECIPES_OFFSET;

        List<Recipe> recipeList = GodtCloudProvider.getInstance().getRecipesPage(loadedRecipes, offset);

        try {
            persistenceManager.persistRecipes(recipeList);
            appController.setLoadedRecipesCount(loadedRecipes + offset);
        } catch (Exception ignored) {
        }

        mainThreadHandler.sendEmptyMessage(0);
    }

    private static class MainThreadHandler extends Handler {

        public MainThreadHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            BusProvider.getInstance().post(new RecipesLoadedEvent());
        }
    }

}
