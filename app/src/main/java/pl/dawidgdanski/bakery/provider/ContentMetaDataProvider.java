package pl.dawidgdanski.bakery.provider;

import android.content.UriMatcher;
import android.net.Uri;

import pl.dawidgdanski.bakery.database.contract.Contracts;
import pl.dawidgdanski.bakery.database.contract.ElementContract;
import pl.dawidgdanski.bakery.database.contract.FtsRecipeWithIngredientContract;
import pl.dawidgdanski.bakery.database.contract.IngredientContract;
import pl.dawidgdanski.bakery.database.contract.RecipeContract;

final class ContentMetaDataProvider {

    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

        URI_MATCHER.addURI(
                AppContentProvider.AUTHORITY,
                ElementContract.Table.TABLE_NAME,
                ContentMetaData.ELEMENT_COLLECTION_URI_INDICATOR);
        URI_MATCHER.addURI(
                AppContentProvider.AUTHORITY,
                ElementContract.Table.TABLE_NAME + "/#",
                ContentMetaData.ELEMENT_SINGLE_ITEM_URI_INDICATOR);
        URI_MATCHER.addURI(
                AppContentProvider.AUTHORITY,
                IngredientContract.Table.TABLE_NAME,
                ContentMetaData.INGREDIENT_COLLECTION_URI_INDICATOR);
        URI_MATCHER.addURI(
                AppContentProvider.AUTHORITY,
                IngredientContract.Table.TABLE_NAME + "/#",
                ContentMetaData.INGREDIENT_SINGLE_ITEM_URI_INDICATOR);
        URI_MATCHER.addURI(
                AppContentProvider.AUTHORITY,
                RecipeContract.Table.TABLE_NAME,
                ContentMetaData.RECIPE_COLLECTION_URI_INDICATOR);
        URI_MATCHER.addURI(
                AppContentProvider.AUTHORITY,
                RecipeContract.Table.TABLE_NAME + "/#",
                ContentMetaData.RECIPE_SINGLE_ITEM_URI_INDICATOR);
        URI_MATCHER.addURI(
                AppContentProvider.AUTHORITY,
                FtsRecipeWithIngredientContract.Table.TABLE_NAME,
                ContentMetaData.FTS_RECIPE_WITH_INGREDIENT_COLLECTION_URI_INDICATOR);
    }

    private ContentMetaDataProvider() {
    }

    static ContentMetaData matchContentMetaData(final Uri uri) {
        final int code = URI_MATCHER.match(uri);

        for(ContentMetaData contentMetaData : Contracts.getContentMetaDataHolders()) {
            if(contentMetaData.getCode() == code) {
                return contentMetaData;
            }
        }

        throw new IllegalArgumentException("Unsupported Uri:  " + uri.getEncodedPath());
    }
}
