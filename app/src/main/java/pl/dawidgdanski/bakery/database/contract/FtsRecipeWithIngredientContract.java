package pl.dawidgdanski.bakery.database.contract;

import android.net.Uri;
import android.provider.BaseColumns;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public final class FtsRecipeWithIngredientContract {

    private FtsRecipeWithIngredientContract() { }

    public static final Uri CONTENT_URI = Uri.parse(Contracts.BASE_CONTENT_URI + "/" + Table.TABLE_NAME);

    public static final Map<String, String> PROJECTION_MAP = ImmutableMap.<String, String>builder()
            .put(Table._ID, Table.DOC_ID)
            .put(Table.COLUMN_RECIPE_ID, Table.COLUMN_RECIPE_ID)
            .put(Table.COLUMN_TITLE, Table.COLUMN_TITLE)
            .put(Table.COLUMN_DESCRIPTION, Table.COLUMN_DESCRIPTION)
            .put(Table.COLUMN_IMAGE_URL, Table.COLUMN_IMAGE_URL)
            .put(Table.COLUMN_INGREDIENT_NAME, Table.COLUMN_INGREDIENT_NAME)
            .put(Table.COLUMN_ELEMENT_NAMES, Table.COLUMN_ELEMENT_NAMES)
            .build();

    public static final String DEFAULT_SORT_ORDER = String.format(
            "%s, %s ASC",
            Table.COLUMN_TITLE,
            Table.COLUMN_DESCRIPTION);

    public static final String CONTENT_TYPE_COLLECTION = String.format("%s.recipes_with_ingredients", Contracts.BASE_COLLECTION_TYPE);

    public static final Collection<Uri> BOUND_NOTIFICATION_URIS = Collections.EMPTY_LIST;

    public static final String STANDARD_MATCH = Joiner.on(" ").join(Table.TABLE_NAME, "MATCH", "?");

    public static final class Table implements BaseColumns {

        private Table() { }

        public static final String TABLE_NAME = "fts_recipe_with_ingredient";

        public static final String DOC_ID = "docid";

        public static final String COLUMN_RECIPE_ID = RecipeContract.Table.COLUMN_RECIPE_ID;

        public static final String COLUMN_TITLE = RecipeContract.Table.COLUMN_TITLE;

        public static final String COLUMN_DESCRIPTION = RecipeContract.Table.COLUMN_DESCRIPTION;

        public static final String COLUMN_IMAGE_URL = RecipeContract.Table.COLUMN_IMAGE_URL;

        public static final String COLUMN_INGREDIENT_NAME = "ingredient_name";

        public static final String COLUMN_ELEMENT_NAMES = "element_names";
    }

}
