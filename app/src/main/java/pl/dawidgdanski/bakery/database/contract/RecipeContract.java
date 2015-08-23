package pl.dawidgdanski.bakery.database.contract;

import android.net.Uri;
import android.provider.BaseColumns;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.Map;

public final class RecipeContract {

    private RecipeContract() { }

    public static final Uri CONTENT_URI = Uri.parse(Joiner.on("/").join(Contracts.BASE_CONTENT_URI, Table.TABLE_NAME));

    public static final Map<String, String> PROJECTION = ImmutableMap.<String, String>builder()
            .put(Table._ID, Table._ID)
            .put(Table.COLUMN_DESCRIPTION, Table.COLUMN_DESCRIPTION)
            .put(Table.COLUMN_IMAGE_URL, Table.COLUMN_IMAGE_URL)
            .put(Table.COLUMN_RECIPE_ID, Table.COLUMN_RECIPE_ID)
            .put(Table.COLUMN_TITLE, Table.COLUMN_TITLE)
            .build();

    public static Collection<Uri> BOUND_NOTIFICATION_URIS = ImmutableSet.<Uri>builder()
            .add(FtsRecipeWithIngredientContract.CONTENT_URI)
            .build();

    public static final String CONTENT_TYPE_COLLECTION = String.format("%s.recipes", Contracts.BASE_COLLECTION_TYPE);

    public static final String CONTENT_TYPE_SINGLE_ITEM = String.format("%s.recipe", Contracts.BASE_SINGLE_ITEM_TYPE);

    public static final String DEFAULT_SORT_ORDER = String.format(
            "%s, %s ASC",
            Table.COLUMN_TITLE,
            Table.COLUMN_DESCRIPTION
    );

    public static class Table implements BaseColumns {

        public static final String TABLE_NAME = "recipe";

        public static final String COLUMN_RECIPE_ID = "recipe_id";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_DESCRIPTION = "description";

        public static final String COLUMN_IMAGE_URL = "image_url";

    }

}
