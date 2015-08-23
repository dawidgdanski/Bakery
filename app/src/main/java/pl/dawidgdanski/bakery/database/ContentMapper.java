package pl.dawidgdanski.bakery.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dawidgdanski.bakery.database.contract.RecipeContract;
import pl.dawidgdanski.bakery.library.model.Recipe;
import pl.dawidgdanski.bakery.library.model.RecipeImpl;

public final class ContentMapper {

    private static final int NO_COLUMN_ID = -1;

    private ContentMapper() {
    }

    public static ContentValues toContentValues(Recipe recipe) {
        Preconditions.checkNotNull(recipe, "recipe is null");

        ContentValues contentValues = new ContentValues();
        contentValues.put(RecipeContract.Table.COLUMN_RECIPE_ID, recipe.getId());
        contentValues.put(RecipeContract.Table.COLUMN_DESCRIPTION, recipe.getDescription());
        contentValues.put(RecipeContract.Table.COLUMN_IMAGE_URL, recipe.getImageUrl());
        contentValues.put(RecipeContract.Table.COLUMN_TITLE, recipe.getTitle());

        return contentValues;
    }

    public static Recipe toSingleRecipe(Cursor cursor) {

        Preconditions.checkState(cursor.getCount() == 1);

        if (cursor.moveToFirst()) {

            return new RecipeImpl.Builder()
                    .setDatabaseId(getInt(cursor, cursor.getColumnIndex(RecipeContract.Table._ID), 0))
                    .setImageUrl(getString(cursor, cursor.getColumnIndex(RecipeContract.Table.COLUMN_IMAGE_URL), null))
                    .setId(getString(cursor, cursor.getColumnIndex(RecipeContract.Table.COLUMN_RECIPE_ID), null))
                    .setTitle(getString(cursor, cursor.getColumnIndex(RecipeContract.Table.COLUMN_TITLE), null))
                    .setDescription(getString(cursor, cursor.getColumnIndex(RecipeContract.Table.COLUMN_DESCRIPTION), null))
                    .build();
        }

        return null;
    }

    public static List<Recipe> toRecipeList(final Cursor cursor) {
        Preconditions.checkNotNull(cursor, "Cursor is null");

        List<Recipe> recipes = new ArrayList<>(cursor.getCount());

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                recipes.add(toSingleRecipe(cursor));
                cursor.moveToNext();
            }
        }

        return Collections.unmodifiableList(recipes);
    }

    private static String getString(final Cursor cursor, final int columnId, final String defaultValue) {
        return columnId == NO_COLUMN_ID ? defaultValue : cursor.getString(columnId);
    }

    private static int getInt(final Cursor cursor, final int columnId, final int defaultValue) {
        return columnId == NO_COLUMN_ID ? defaultValue : cursor.getInt(columnId);
    }
}
