package pl.dawidgdanski.bakery.database.contract;

import android.net.Uri;
import android.provider.BaseColumns;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public final class FtsRecipeWithIngredientContract {

    private FtsRecipeWithIngredientContract() { }

    public static final Uri CONTENT_URI = Uri.parse(Joiner.on("/").join(Contracts.BASE_CONTENT_URI, Table.TABLE_NAME));

    public static final Map<String, String> PROJECTION_MAP = ImmutableMap.<String, String>builder()
            .put(Table._ID, Table.DOC_ID)
            .put(Table.COLUMN_RECIPE_ID, Table.COLUMN_RECIPE_ID)
            .put(Table.COLUMN_TITLE, Table.COLUMN_TITLE)
            .put(Table.COLUMN_DESCRIPTION, Table.COLUMN_DESCRIPTION)
            .put(Table.COLUMN_IMAGE_URL, Table.COLUMN_IMAGE_URL)
            .build();

    public static final class Table implements BaseColumns {

        private Table() { }

        public static final String TABLE_NAME = "fts_recipe_with_ingredient";

        public static final String DOC_ID = "docid";

        public static final String COLUMN_RECIPE_ID = RecipeContract.Table.COLUMN_RECIPE_ID;

        public static final String COLUMN_TITLE = RecipeContract.Table.COLUMN_TITLE;

        public static final String COLUMN_DESCRIPTION = RecipeContract.Table.COLUMN_DESCRIPTION;

        public static final String COLUMN_IMAGE_URL = RecipeContract.Table.COLUMN_IMAGE_URL;

    }

}
