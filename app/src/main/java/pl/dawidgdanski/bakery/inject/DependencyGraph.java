package pl.dawidgdanski.bakery.inject;

import javax.inject.Singleton;

import dagger.Component;
import pl.dawidgdanski.bakery.App;
import pl.dawidgdanski.bakery.inject.module.ApiModule;
import pl.dawidgdanski.bakery.inject.module.ApplicationModule;
import pl.dawidgdanski.bakery.inject.module.BusModule;
import pl.dawidgdanski.bakery.inject.module.CloudModule;
import pl.dawidgdanski.bakery.inject.module.DatabaseModule;
import pl.dawidgdanski.bakery.provider.AppContentProvider;
import pl.dawidgdanski.bakery.service.RecipesDownloadService;
import pl.dawidgdanski.bakery.ui.fragment.RecipeListFragment;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        CloudModule.class,
        BusModule.class,
        ApiModule.class,
        DatabaseModule.class})
public interface DependencyGraph {

    void inject(AppContentProvider appContentProvider);

    void inject(RecipesDownloadService recipesDownloadService);

    void inject(RecipeListFragment recipeListFragment);

    void inject(App app);
}
