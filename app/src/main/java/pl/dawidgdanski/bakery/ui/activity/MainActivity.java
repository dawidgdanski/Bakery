package pl.dawidgdanski.bakery.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.dawidgdanski.bakery.R;
import pl.dawidgdanski.bakery.ui.fragment.RecipeListFragment;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ButterKnife.bind(this);

        setUpActionBar(toolbar);
        setUpActionBarTitle(getString(R.string.recipes));

        if(savedInstanceState == null) {
            intializeFragment();
        }
    }

    private void intializeFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, RecipeListFragment.newInstance())
                .commit();
    }
}
