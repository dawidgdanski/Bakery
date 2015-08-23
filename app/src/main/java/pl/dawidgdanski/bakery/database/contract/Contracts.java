package pl.dawidgdanski.bakery.database.contract;

import android.content.ContentResolver;
import android.net.Uri;

import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.Map;

import pl.dawidgdanski.bakery.provider.AppContentProvider;
import pl.dawidgdanski.bakery.provider.ContentMetaData;

public final class Contracts {

    private Contracts() { }

    private static final Collection<Uri> RESOURCE_URIS = ImmutableSet.<Uri>builder()
            .add(ElementContract.CONTENT_URI)
            .add(FtsRecipeWithIngredientContract.CONTENT_URI)
            .add(IngredientContract.CONTENT_URI)
            .add(RecipeContract.CONTENT_URI)
            .build();

    public static final String BASE_COLLECTION_TYPE = String.format("%s/vnd.pl.dawidgdanski.bakery",
            ContentResolver.CURSOR_DIR_BASE_TYPE);

    public static final String BASE_SINGLE_ITEM_TYPE = String.format("%s/vnd.pl.dawidgdanski.bakery",
            ContentResolver.CURSOR_ITEM_BASE_TYPE);

    public static final String BASE_CONTENT_URI = String.format("content://%s", AppContentProvider.AUTHORITY);

    public static final String FTS_SELECTION_DOC_ID_AS_ID = "docid AS _id";

    public static Collection<Uri> getResourceUris() {
        return RESOURCE_URIS;
    }

    public static ContentMetaData[] getContentMetaDataHolders() {
        return ContentMetaDataHolder.values();
    }

    private enum ContentMetaDataHolder implements ContentMetaData {

        ELEMENTS_COLLECTION(
                ELEMENT_COLLECTION_URI_INDICATOR,
                ElementContract.CONTENT_URI,
                ElementContract.Table.TABLE_NAME,
                ElementContract.PROJECTION,
                ElementContract.DEFAULT_SORT_ORDER,
                ElementContract.CONTENT_TYPE_COLLECTION,
                ElementContract.BOUND_NOTIFICATION_URIS,
                false),

        ELEMENTS_ITEM(
                ELEMENT_SINGLE_ITEM_URI_INDICATOR,
                ElementContract.CONTENT_URI,
                ElementContract.Table.TABLE_NAME,
                ElementContract.PROJECTION,
                ElementContract.DEFAULT_SORT_ORDER,
                ElementContract.CONTENT_TYPE_SINGLE_ITEM,
                ElementContract.BOUND_NOTIFICATION_URIS,
                true),

        INGREDIENTS_COLLECTION(
                INGREDIENT_COLLECTION_URI_INDICATOR,
                IngredientContract.CONTENT_URI,
                IngredientContract.Table.TABLE_NAME,
                IngredientContract.PROJECTION,
                IngredientContract.DEFAULT_SORT_ORDER,
                IngredientContract.CONTENT_TYPE_COLLECTION,
                IngredientContract.BOUND_NOTIFICATION_URIS,
                false),

        INGREDIENTS_ITEM(
                INGREDIENT_SINGLE_ITEM_URI_INDICATOR,
                IngredientContract.CONTENT_URI,
                IngredientContract.Table.TABLE_NAME,
                IngredientContract.PROJECTION,
                IngredientContract.DEFAULT_SORT_ORDER,
                IngredientContract.CONTENT_TYPE_SINGLE_ITEM,
                IngredientContract.BOUND_NOTIFICATION_URIS,
                true),

        RECIPES_COLLECTION(
                RECIPE_COLLECTION_URI_INDICATOR,
                RecipeContract.CONTENT_URI,
                RecipeContract.Table.TABLE_NAME,
                RecipeContract.PROJECTION,
                RecipeContract.DEFAULT_SORT_ORDER,
                RecipeContract.CONTENT_TYPE_COLLECTION,
                RecipeContract.BOUND_NOTIFICATION_URIS,
                false),

        RECIPES_ITEM(
                RECIPE_SINGLE_ITEM_URI_INDICATOR,
                RecipeContract.CONTENT_URI,
                RecipeContract.Table.TABLE_NAME,
                RecipeContract.PROJECTION,
                RecipeContract.DEFAULT_SORT_ORDER,
                RecipeContract.CONTENT_TYPE_SINGLE_ITEM,
                RecipeContract.BOUND_NOTIFICATION_URIS,
                true);

        private final Uri uri;

        private final String tableName;

        private final Map<String, String> projectionMap;

        private final String defaultSortOrder;

        private final String contentType;

        private final Collection<Uri> boundUris;

        private final int code;

        private final boolean isSingeItemType;

        ContentMetaDataHolder(int code,
                              Uri uri,
                              String tableName,
                              Map<String, String> projectionMap,
                              String defaultSortOrder,
                              String contentType,
                              Collection<Uri> boundUris,
                              boolean isSingeItemType) {
            this.code = code;
            this.uri = uri;
            this.tableName = tableName;
            this.projectionMap = projectionMap;
            this.defaultSortOrder = defaultSortOrder;
            this.contentType = contentType;
            this.boundUris = boundUris;
            this.isSingeItemType = isSingeItemType;
        }


        @Override
        public int getCode() {
            return code;
        }

        @Override
        public Uri getUri() {
            return uri;
        }

        @Override
        public String getTableName() {
            return tableName;
        }

        @Override
        public Map<String, String> getProjectionMap() {
            return projectionMap;
        }

        @Override
        public String getDefaultSortOrder() {
            return defaultSortOrder;
        }

        @Override
        public String getContentType() {
            return contentType;
        }

        @Override
        public Collection<Uri> getBoundNotificationUris() {
            return boundUris;
        }

        @Override
        public boolean isSingleItemType() {
            return isSingeItemType;
        }
    }

}
