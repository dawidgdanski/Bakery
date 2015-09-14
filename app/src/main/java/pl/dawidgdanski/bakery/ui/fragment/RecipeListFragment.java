package pl.dawidgdanski.bakery.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.dawidgdanski.bakery.R;
import pl.dawidgdanski.bakery.controller.AppController;
import pl.dawidgdanski.bakery.database.contract.Contracts;
import pl.dawidgdanski.bakery.database.contract.FtsRecipeWithIngredientContract;
import pl.dawidgdanski.bakery.event.RecipesLoadedEvent;
import pl.dawidgdanski.bakery.exception.SwipeValidationException;
import pl.dawidgdanski.bakery.inject.DependencyInjector;
import pl.dawidgdanski.bakery.service.RecipesDownloadService;
import pl.dawidgdanski.bakery.ui.adapter.RecipesCursorAdapter;
import pl.dawidgdanski.bakery.util.AppUtils;

public class RecipeListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        LoaderManager.LoaderCallbacks<Cursor>,
        SearchView.OnQueryTextListener {

    public static RecipeListFragment newInstance() {

        RecipeListFragment fragment = new RecipeListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }

    private static final String LOADER_EXTRA_QUERY = "query";

    private static final int LOADER_ID_RECIPES_WITH_INGREDIENTS = 1;

    private static final String[] RECIPES_WITH_INGREDIENTS_PROJECTION = new String[]{
            Contracts.FTS_SELECTION_DOC_ID_AS_ID,
            FtsRecipeWithIngredientContract.Table.COLUMN_TITLE,
            FtsRecipeWithIngredientContract.Table.COLUMN_IMAGE_URL,
            FtsRecipeWithIngredientContract.Table.COLUMN_INGREDIENT_NAME
    };

    @Bind(R.id.empty_list_text)
    TextView emptyListText;

    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(android.R.id.list)
    ListView listView;

    @Inject
    Bus bus;

    @Inject
    AppController appController;

    private SimpleCursorAdapter recipesAdapter;

    private Bundle searchQueryBundle = new Bundle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DependencyInjector.getGraph().inject(this);
        setHasOptionsMenu(true);
        bus.register(this);
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
        listView.setEmptyView(emptyListText);
        listView.setAdapter(recipesAdapter);
        getLoaderManager().initLoader(LOADER_ID_RECIPES_WITH_INGREDIENTS, searchQueryBundle, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.recipe_list_fragment, menu);

        final MenuItem searchMenuItem = menu.findItem(R.id.search_recipes);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchView.setOnQueryTextListener(this);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    searchMenuItem.collapseActionView();
                    searchView.setQuery("", false);
                }
            }
        });
    }

    @Override
    public void onRefresh() {

        try {
            validateInternetConnection();

            validateRecipesCount();

            swipeRefreshLayout.setRefreshing(true);

            final Context context = getActivity();
            context.startService(new Intent(context, RecipesDownloadService.class));

        } catch (SwipeValidationException e) {
            swipeRefreshLayout.setRefreshing(false);
            View view = getView();
            if(view != null) {
                Snackbar.make(view, getString(e.getErrorStringId()), Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Subscribe
    public void onRecipesLoadedEvent(RecipesLoadedEvent event) {
        if(AppUtils.isThisThreadAMainOne()) {
            swipeRefreshLayout.setRefreshing(false);
        } else {
            AppUtils.runOnUiThread(getActivity(), new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(getActivity());
        loader.setUri(FtsRecipeWithIngredientContract.CONTENT_URI);
        loader.setProjection(RECIPES_WITH_INGREDIENTS_PROJECTION);

        final String searchQuery = args.getString(LOADER_EXTRA_QUERY, "");

        if(!TextUtils.isEmpty(searchQuery)) {
            loader.setSelection(FtsRecipeWithIngredientContract.STANDARD_MATCH);
            loader.setSelectionArgs(new String[] {
                    String.format("%s*", searchQuery)
            });
        }

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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        searchQueryBundle.putString(LOADER_EXTRA_QUERY, newText);
        getLoaderManager().restartLoader(LOADER_ID_RECIPES_WITH_INGREDIENTS, searchQueryBundle, this);
        return true;
    }

    private void validateInternetConnection() throws SwipeValidationException {
        if(! appController.isDeviceOnline()) {
            throw new SwipeValidationException(R.string.device_is_offline);
        }
    }

    private void validateRecipesCount() throws SwipeValidationException {
        if(appController.areAllRecipesLoaded()) {
            throw new SwipeValidationException(R.string.all_recipes_loaded);
        }
    }
}
