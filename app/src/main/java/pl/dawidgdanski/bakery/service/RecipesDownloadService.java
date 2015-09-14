package pl.dawidgdanski.bakery.service;

import android.app.IntentService;
import android.content.Intent;

import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import pl.dawidgdanski.bakery.controller.AppController;
import pl.dawidgdanski.bakery.controller.PersistenceManager;
import pl.dawidgdanski.bakery.event.RecipesLoadedEvent;
import pl.dawidgdanski.bakery.inject.DependencyInjector;
import pl.dawidgdanski.bakery.library.cloud.GodtCloud;
import pl.dawidgdanski.bakery.library.model.Recipe;

public class RecipesDownloadService extends IntentService {

    private static final String TAG = RecipesDownloadService.class.getSimpleName();

    @Inject
    PersistenceManager persistenceManager;

    @Inject
    GodtCloud godtCloud;

    @Inject
    AppController appController;

    @Inject
    Bus bus;

    public RecipesDownloadService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DependencyInjector.getGraph().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        final int loadedRecipes = appController.getLoadedRecipesCount();
        final int offset = AppController.RECIPES_OFFSET;

        List<Recipe> recipeList = godtCloud.getRecipesPage(loadedRecipes, offset);

        try {
            persistenceManager.persistRecipes(recipeList);
            appController.setLoadedRecipesCount(loadedRecipes + offset);
        } catch (Exception ignored) {
        }

        bus.post(new RecipesLoadedEvent());
    }

}
