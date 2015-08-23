package pl.dawidgdanski.bakery.service;

import android.app.IntentService;
import android.content.Intent;

import pl.dawidgdanski.bakery.controller.AppController;

public class RecipesDownloadService extends IntentService {

    private static final String TAG = RecipesDownloadService.class.getSimpleName();

    public RecipesDownloadService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        AppController appController = AppController.getInstance(this);



    }
}
