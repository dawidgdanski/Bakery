package pl.dawidgdanski.bakery.database.contract;

import android.net.Uri;
import android.provider.BaseColumns;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.Map;

public final class IngredientContract {

    private IngredientContract() { }

    public static final Uri CONTENT_URI = Uri.parse(Joiner.on("/").join(Contracts.BASE_CONTENT_URI, Table.TABLE_NAME));

    public static final Map<String, String> PROJECTION = ImmutableMap.<String, String>builder()
            .put(Table._ID, Table._ID)
            .put(Table.RECIPE_ID, Table.RECIPE_ID)
            .put(Table.INGREDIENT_ID, Table.INGREDIENT_ID)
            .put(Table.NAME, Table.NAME)
            .build();

    public static Collection<Uri> BOUND_NOTIFICATION_URIS = ImmutableSet.<Uri>builder()
            .add(FtsRecipeWithIngredientContract.CONTENT_URI)
            .build();

    public static final String CONTENT_TYPE_COLLECTION = String.format("%s.ingredients", Contracts.BASE_COLLECTION_TYPE);

    public static final String CONTENT_TYPE_SINGLE_ITEM = String.format("%s.ingredient", Contracts.BASE_SINGLE_ITEM_TYPE);

    public static final String DEFAULT_SORT_ORDER = String.format(
            "%s ASC",
            Table.NAME
    );

    public static final class Table implements BaseColumns {

        private Table() { }

        public static final String TABLE_NAME = "ingredient";

        public static final String RECIPE_ID = "recipe_id";

        public static final String INGREDIENT_ID = "ingredient_id";

        public static final String NAME = "name";
    }
}
