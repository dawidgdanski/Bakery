package pl.dawidgdanski.bakery.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.dawidgdanski.bakery.database.contract.ElementContract;
import pl.dawidgdanski.bakery.database.contract.IngredientContract;
import pl.dawidgdanski.bakery.database.contract.RecipeContract;
import pl.dawidgdanski.bakery.library.model.Element;
import pl.dawidgdanski.bakery.library.model.ElementImpl;
import pl.dawidgdanski.bakery.library.model.Ingredient;
import pl.dawidgdanski.bakery.library.model.IngredientImpl;
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

    public static ContentValues toContentValues(final Element element, final String ingredientId) {
        Preconditions.checkNotNull(element, "element is null");

        ContentValues contentValues = new ContentValues();
        contentValues.put(ElementContract.Table.COLUMN_SYMBOL, element.getSymbol());
        contentValues.put(ElementContract.Table.COLUMN_AMOUNT, element.getAmount());
        contentValues.put(ElementContract.Table.COLUMN_UNIT_NAME, element.getUnitName());
        contentValues.put(ElementContract.Table.COLUMN_HINT, element.getHint());
        contentValues.put(ElementContract.Table.COLUMN_NAME, element.getName());
        contentValues.put(ElementContract.Table.COLUMN_INGREDIENT_ID, ingredientId);
        contentValues.put(ElementContract.Table.COLUMN_ELEMENT_ID, element.getId());

        return contentValues;
    }

    public static ContentValues toContentValues(Ingredient ingredient, String recipeId) {
        Preconditions.checkNotNull(ingredient, "Ingredient is null");

        ContentValues contentValues = new ContentValues();
        contentValues.put(IngredientContract.Table.COLUMN_INGREDIENT_ID, ingredient.getId());
        contentValues.put(IngredientContract.Table.COLUMN_NAME, ingredient.getName());
        contentValues.put(IngredientContract.Table.COLUMN_RECIPE_ID, recipeId);

        return contentValues;
    }

    public static Element toSingleElement(Cursor cursor) {
        return transformToEntity(cursor, new Function<Cursor, Element>() {
            @Override
            public Element apply(Cursor input) {
                return toElement(input);
            }
        });
    }

    public static List<Element> toElementList(Cursor cursor) {
        return transformToEntityList(cursor, new Function<Cursor, Element>() {
            @Override
            public Element apply(Cursor input) {
                return toElement(input);
            }
        });
    }

    private static Element toElement(Cursor cursor) {

        return new ElementImpl.Builder()
                .setUnitName(getString(cursor, cursor.getColumnIndex(ElementContract.Table.COLUMN_UNIT_NAME), null))
                .setSymbol(getString(cursor, cursor.getColumnIndex(ElementContract.Table.COLUMN_SYMBOL), null))
                .setHint(getString(cursor, cursor.getColumnIndex(ElementContract.Table.COLUMN_HINT), null))
                .setAmount(getInt(cursor, cursor.getColumnIndex(ElementContract.Table.COLUMN_AMOUNT), 0))
                .setName(getString(cursor, cursor.getColumnIndex(ElementContract.Table.COLUMN_NAME), null))
                .setId(getString(cursor, cursor.getColumnIndex(ElementContract.Table.COLUMN_ELEMENT_ID), null))
                .setDatabaseId(getInt(cursor, cursor.getColumnIndex(ElementContract.Table._ID), -1))
                .build();
    }

    public static Recipe toSingleRecipe(Cursor cursor) {
        return transformToEntity(cursor, new Function<Cursor, Recipe>() {
            @Override
            public Recipe apply(Cursor input) {
                return toRecipe(input);
            }
        });
    }

    private static Recipe toRecipe(Cursor cursor) {
        return new RecipeImpl.Builder()
                .setDatabaseId(getInt(cursor, cursor.getColumnIndex(RecipeContract.Table._ID), 0))
                .setImageUrl(getString(cursor, cursor.getColumnIndex(RecipeContract.Table.COLUMN_IMAGE_URL), null))
                .setId(getString(cursor, cursor.getColumnIndex(RecipeContract.Table.COLUMN_RECIPE_ID), null))
                .setTitle(getString(cursor, cursor.getColumnIndex(RecipeContract.Table.COLUMN_TITLE), null))
                .setDescription(getString(cursor, cursor.getColumnIndex(RecipeContract.Table.COLUMN_DESCRIPTION), null))
                .build();
    }

    public static List<Recipe> toRecipeList(Cursor cursor) {
        Preconditions.checkNotNull(cursor, "Cursor is null");

        return transformToEntityList(cursor, new Function<Cursor, Recipe>() {
            @Override
            public Recipe apply(Cursor input) {
                return toRecipe(input);
            }
        });
    }

    public static Ingredient toSingleIngredient(Cursor cursor) {
        Preconditions.checkState(cursor.getCount() == 1);

        if (cursor.moveToFirst()) {
            return toIngredient(cursor);
        }

        return null;
    }

    private static Ingredient toIngredient(final Cursor cursor) {
        return new IngredientImpl.Builder()
                .setDatabaseId(getInt(cursor, cursor.getColumnIndex(IngredientContract.Table._ID), 0))
                .setId(getString(cursor, cursor.getColumnIndex(IngredientContract.Table.COLUMN_INGREDIENT_ID), null))
                .setName(getString(cursor, cursor.getColumnIndex(IngredientContract.Table.COLUMN_NAME), null))
                .build();
    }

    public static List<Ingredient> toIngredientList(final Cursor cursor) {
        Preconditions.checkNotNull(cursor, "Cursor is null");

        return transformToEntityList(cursor, new Function<Cursor, Ingredient>() {
            @Override
            public Ingredient apply(Cursor input) {
                return toIngredient(cursor);
            }
        });
    }

    private static <T> List<T> transformToEntityList(final Cursor cursor, final Function<Cursor, T> transformation) {
        List<T> itemList = new ArrayList<>(cursor.getCount());

        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                itemList.add(transformation.apply(cursor));
                cursor.moveToNext();
            }
        }

        return Collections.unmodifiableList(itemList);
    }

    private static <T> T transformToEntity(Cursor cursor, Function<Cursor, T> transformFunction) {
        Preconditions.checkState(cursor.getCount() == 1);

        if (cursor.moveToFirst()) {
            return transformFunction.apply(cursor);
        }

        return null;
    }

    private static String getString(final Cursor cursor, final int columnId, final String defaultValue) {
        return columnId == NO_COLUMN_ID ? defaultValue : cursor.getString(columnId);
    }

    private static int getInt(final Cursor cursor, final int columnId, final int defaultValue) {
        return columnId == NO_COLUMN_ID ? defaultValue : cursor.getInt(columnId);
    }
}
