package pl.dawidgdanski.bakery.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.dawidgdanski.bakery.R;
import pl.dawidgdanski.bakery.controller.AppController;
import pl.dawidgdanski.bakery.controller.BusProvider;
import pl.dawidgdanski.bakery.database.contract.Contracts;
import pl.dawidgdanski.bakery.database.contract.FtsRecipeWithIngredientContract;
import pl.dawidgdanski.bakery.event.RecipesLoadedEvent;
import pl.dawidgdanski.bakery.service.RecipesDownloadService;
import pl.dawidgdanski.bakery.ui.adapter.RecipesCursorAdapter;

public class RecipeListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    public static RecipeListFragment newInstance() {

        RecipeListFragment fragment = new RecipeListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }

    private static final int LOADER_ID_RECIPES_WITH_INGREDIENTS = 1;

    private static final String[] RECIPES_WITH_INGREDIENTS_PROJECTION = new String[]{
            Contracts.FTS_SELECTION_DOC_ID_AS_ID,
            FtsRecipeWithIngredientContract.Table.COLUMN_TITLE,
            FtsRecipeWithIngredientContract.Table.COLUMN_IMAGE_URL,
            FtsRecipeWithIngredientContract.Table.COLUMN_INGREDIENT_NAME
    };

    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(android.R.id.list)
    ListView listView;

    private SimpleCursorAdapter recipesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        BusProvider.getInstance().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        swipeRefreshLayout.setOnRefreshListener(this);
        recipesAdapter = new RecipesCursorAdapter(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setAdapter(recipesAdapter);
        getLoaderManager().initLoader(LOADER_ID_RECIPES_WITH_INGREDIENTS, null, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onRefresh() {
        final Context context = getActivity();
        AppController appController = AppController.getInstance();

        final boolean areAllRecipesLoaded = appController.areAllRecipesLoaded();
        final boolean isDeviceOnline = appController.isDeviceOnline(context);

        if (context != null && !areAllRecipesLoaded && isDeviceOnline) {
            context.startService(new Intent(context, RecipesDownloadService.class));
            swipeRefreshLayout.setRefreshing(true);
        } else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Subscribe
    public void onRecipesLoadedEvent(RecipesLoadedEvent event) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(getActivity());
        loader.setUri(FtsRecipeWithIngredientContract.CONTENT_URI);
        loader.setProjection(RECIPES_WITH_INGREDIENTS_PROJECTION);
        //loader.setSelection(FtsRecipeWithIngredientContract.STANDARD_MATCH);

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        recipesAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        recipesAdapter.swapCursor(null);
    }
}
