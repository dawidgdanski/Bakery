package pl.dawidgdanski.bakery.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecipeListFragment extends ListFragment {

    public static RecipeListFragment newInstance() {

        RecipeListFragment fragment = new RecipeListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
