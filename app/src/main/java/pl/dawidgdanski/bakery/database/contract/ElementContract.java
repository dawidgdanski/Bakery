package pl.dawidgdanski.bakery.database.contract;

import android.net.Uri;
import android.provider.BaseColumns;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.Map;

public final class ElementContract {

    private ElementContract() { }

    public static final Uri CONTENT_URI = Uri.parse(Joiner.on("/").join(Contracts.BASE_CONTENT_URI, Table.TABLE_NAME));

    public static final Map<String, String> PROJECTION = ImmutableMap.<String, String>builder()
            .put(Table._ID, Table._ID)
            .put(Table.COLUMN_AMOUNT, Table.COLUMN_AMOUNT)
            .put(Table.COLUMN_ELEMENT_ID, Table.COLUMN_ELEMENT_ID)
            .put(Table.COLUMN_INGREDIENT_ID, Table.COLUMN_INGREDIENT_ID)
            .put(Table.COLUMN_NAME, Table.COLUMN_NAME)
            .put(Table.COLUMN_HINT, Table.COLUMN_HINT)
            .put(Table.COLUMN_UNIT_NAME, Table.COLUMN_UNIT_NAME)
            .put(Table.COLUMN_SYMBOL, Table.COLUMN_SYMBOL)
            .build();

    public static Collection<Uri> BOUND_NOTIFICATION_URIS = ImmutableSet.<Uri>builder()
            .add(FtsRecipeWithIngredientContract.CONTENT_URI)
            .build();

    public static final String CONTENT_TYPE_COLLECTION = String.format("%s.elements", Contracts.BASE_COLLECTION_TYPE);

    public static final String CONTENT_TYPE_SINGLE_ITEM = String.format("%s.element", Contracts.BASE_SINGLE_ITEM_TYPE);

    public static final String DEFAULT_SORT_ORDER = String.format(
            "%s, %s ASC",
            Table.COLUMN_NAME,
            Table.COLUMN_HINT
    );

    public static final class Table implements BaseColumns {

        private Table() { }

        public static final String TABLE_NAME = "element";
        
        public static final String COLUMN_ELEMENT_ID = "element_id";

        public static final String COLUMN_INGREDIENT_ID = "ingredient_id";

        public static final String COLUMN_NAME = "name";

        public static final String COLUMN_AMOUNT = "amount";

        public static final String COLUMN_HINT = "hint";

        public static final String COLUMN_UNIT_NAME = "unit_name";

        public static final String COLUMN_SYMBOL = "symbol";
    }

}
