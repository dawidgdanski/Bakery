package pl.dawidgdanski.bakery.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import pl.dawidgdanski.bakery.R;
import pl.dawidgdanski.bakery.database.DatabaseUtils;
import pl.dawidgdanski.bakery.database.contract.FtsRecipeWithIngredientContract;

public class RecipesCursorAdapter extends SimpleCursorAdapter {

    public static final String[] PROJECTION = new String[]{
            FtsRecipeWithIngredientContract.Table.COLUMN_TITLE,
            FtsRecipeWithIngredientContract.Table.COLUMN_IMAGE_URL
    };

    private static final int[] BOUND_VIEW_IDS = new int[]{
            R.id.recipe_title,
            R.id.recipe_image
    };

    public RecipesCursorAdapter(Context context) {
        super(context,
                R.layout.adapter_recipe_item,
                DatabaseUtils.returnEmptyOrSameCursor(null),
                PROJECTION,
                BOUND_VIEW_IDS,
                0);

        setViewBinder(new AdapterViewBinder(context.getApplicationContext()));
    }

    private static class AdapterViewBinder implements ViewBinder {

        private final Context context;

        private AdapterViewBinder(Context context) {
            this.context = context;
        }

        @Override
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
            switch(view.getId()) {

                case R.id.recipe_title:
                    TextView text = (TextView) view;
                    String textString = cursor.getString(columnIndex);
                    text.setText(textString);
                    return true;

                case R.id.recipe_image:
                    String imageUrl = cursor.getString(columnIndex);
                    Glide.with(context)
                            .load(imageUrl)
                            .centerCrop()
                            .crossFade()
                            .into((ImageView) view);
                    return true;
                default:
                    throw new IllegalStateException();
            }
        }
    }

}
