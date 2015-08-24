package pl.dawidgdanski.bakery.provider;

import android.net.Uri;

import java.util.Collection;
import java.util.Map;

public interface ContentMetaData {

    int INGREDIENT_COLLECTION_URI_INDICATOR = 1;
    int INGREDIENT_SINGLE_ITEM_URI_INDICATOR = 2;

    int RECIPE_COLLECTION_URI_INDICATOR = 3;
    int RECIPE_SINGLE_ITEM_URI_INDICATOR = 4;

    int ELEMENT_COLLECTION_URI_INDICATOR = 5;
    int ELEMENT_SINGLE_ITEM_URI_INDICATOR = 6;

    int FTS_RECIPE_WITH_INGREDIENT_COLLECTION_URI_INDICATOR = 7;

    int getCode();

    Uri getUri();

    String getTableName();

    Map<String, String> getProjectionMap();

    String getDefaultSortOrder();

    String getContentType();

    Collection<Uri> getBoundNotificationUris();

    boolean isSingleItemType();
}
