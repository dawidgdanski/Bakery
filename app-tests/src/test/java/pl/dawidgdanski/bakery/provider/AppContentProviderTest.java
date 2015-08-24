package pl.dawidgdanski.bakery.provider;

import android.annotation.SuppressLint;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;

import pl.dawidgdanski.bakery.AppRobolectricTestRunner;
import pl.dawidgdanski.bakery.database.ContentMapper;
import pl.dawidgdanski.bakery.database.DatabaseHelper;
import pl.dawidgdanski.bakery.database.contract.ElementContract;
import pl.dawidgdanski.bakery.database.contract.IngredientContract;
import pl.dawidgdanski.bakery.database.contract.RecipeContract;
import pl.dawidgdanski.bakery.library.model.Element;
import pl.dawidgdanski.bakery.library.model.ElementImpl;
import pl.dawidgdanski.bakery.library.model.Ingredient;
import pl.dawidgdanski.bakery.library.model.IngredientImpl;
import pl.dawidgdanski.bakery.library.model.Recipe;
import pl.dawidgdanski.bakery.library.model.RecipeImpl;

@RunWith(AppRobolectricTestRunner.class)
public class AppContentProviderTest {

    private final ContentResolver contentResolver;


    public AppContentProviderTest() {
        this.contentResolver = RuntimeEnvironment.application.getContentResolver();
    }

    @Before
    public void setUp() {
        DatabaseHelper.release();
        DatabaseHelper.initialize(RuntimeEnvironment.application);
    }

    @Test
    @SuppressLint("NewApi")
    public void shouldAcquireContentProviderClientForAuthority() {

        ContentProviderClient client = contentResolver.acquireContentProviderClient(AppContentProvider.AUTHORITY);

        Assertions.assertThat(client).isNotNull();
    }

    //Recipe

    @Test
    public void shouldInsertAndRetrieveRecipe() {
        final Recipe recipe = new RecipeImpl.Builder()
                .setDescription("description")
                .setTitle("title")
                .setId("id")
                .setImageUrl("url")
                .build();

        final Uri itemUri = contentResolver.insert(RecipeContract.CONTENT_URI, ContentMapper.toContentValues(recipe));

        Cursor cursor = contentResolver.query(itemUri, null, null, null, null);

        Recipe retrievedRecipe = ContentMapper.toSingleRecipe(cursor);

        Assertions.assertThat(retrievedRecipe).isEqualTo(recipe);
    }

    @Test
    public void shouldUpdateInsertedRecipe() {
        final Recipe recipe = new RecipeImpl.Builder()
                .setDescription("description")
                .setTitle("title")
                .setId("id")
                .setImageUrl("url")
                .build();

        //given
        final Uri itemUri = contentResolver.insert(RecipeContract.CONTENT_URI, ContentMapper.toContentValues(recipe));

        final String newTitle = "NewTitle";
        ContentValues contentValues = new ContentValues();
        contentValues.put(RecipeContract.Table.COLUMN_TITLE, newTitle);

        //when
        final int updatedRows = contentResolver.update(itemUri, contentValues, null, null);

        Cursor cursor = contentResolver.query(itemUri, null, null, null, null);

        Recipe retrievedRecipe = ContentMapper.toSingleRecipe(cursor);

        //then
        Assertions.assertThat(retrievedRecipe.getTitle()).isEqualTo(newTitle);
    }

    @Test
    public void shouldDeleteInsertedRecipe() {
        final Recipe recipe = new RecipeImpl.Builder()
                .setDescription("description")
                .setTitle("title")
                .setId("id")
                .setImageUrl("url")
                .build();

        //given
        final Uri itemUri = contentResolver.insert(RecipeContract.CONTENT_URI, ContentMapper.toContentValues(recipe));

        final int deletionCount = contentResolver.delete(itemUri, null, null);

        Assertions.assertThat(deletionCount).isEqualTo(1);
    }

    //Ingredients
    @Test
    public void shouldInsertAndRetrieveIngredientByRecipeId() {
        Ingredient ingredient = new IngredientImpl.Builder()
                .setId("id")
                .setName("name")
                .build();

        String recipeId = "asdf";

        Uri itemUri = contentResolver.insert(IngredientContract.CONTENT_URI, ContentMapper.toContentValues(ingredient, recipeId));

        String selection = String.format("%s = ?", IngredientContract.Table.COLUMN_RECIPE_ID);

        String[] selectionArgs = new String[] { recipeId };

        Cursor cursor = contentResolver.query(IngredientContract.CONTENT_URI, null, selection, selectionArgs, null);

        Ingredient retrievedIngredient = ContentMapper.toSingleIngredient(cursor);

        Assertions.assertThat(retrievedIngredient).isEqualTo(ingredient);
    }

    @Test
    public void shouldUpdateInsertedIngredientByRecipeId() {
        Ingredient ingredient = new IngredientImpl.Builder()
                .setId("id")
                .setName("name")
                .build();

        String recipeId = "asdf";

        Uri itemUri = contentResolver.insert(IngredientContract.CONTENT_URI, ContentMapper.toContentValues(ingredient, recipeId));

        String newName = "newName";

        ContentValues contentValues = new ContentValues();
        contentValues.put(IngredientContract.Table.COLUMN_NAME, newName);

        String whereClause = String.format("%s = ?", IngredientContract.Table.COLUMN_RECIPE_ID);

        final int updateCount = contentResolver.update(IngredientContract.CONTENT_URI, contentValues, whereClause, new String[]{ recipeId });

        Cursor cursor = contentResolver.query(itemUri, null, null, null, null);

        Ingredient retrievedIngredient = ContentMapper.toSingleIngredient(cursor);

        Assertions.assertThat(retrievedIngredient.getName()).isEqualTo(newName);
    }

    @Test
    public void shouldDeleteInsertedIngredientByRecipeId() {
        Ingredient ingredient = new IngredientImpl.Builder()
                .setId("id")
                .setName("name")
                .build();

        String recipeId = "asdf";

        Uri itemUri = contentResolver.insert(IngredientContract.CONTENT_URI, ContentMapper.toContentValues(ingredient, recipeId));

        String whereClause = String.format("%s = ?", IngredientContract.Table.COLUMN_RECIPE_ID);

        String[] arguments = new String[] { recipeId };

        final int deletionCount = contentResolver.delete(IngredientContract.CONTENT_URI, whereClause, arguments);

        Assertions.assertThat(deletionCount).isEqualTo(1);
    }

    //Elements
    @Test
    public void shouldInsertAndRetrieveElement() {
        Element element = new ElementImpl.Builder()
                .setAmount(1)
                .setHint("Hint")
                .setId("id")
                .setName("Name")
                .setSymbol("Symbol")
                .setUnitName("UnitName")
                .build();

        String recipeId = "recipe";
        String ingredientId = "asdf";

        Uri itemUri = contentResolver.insert(ElementContract.CONTENT_URI, ContentMapper.toContentValues(element, recipeId, ingredientId));

        Cursor cursor = contentResolver.query(itemUri, null, null, null, null);

        Element retrievedElement = ContentMapper.toSingleElement(cursor);

        Assertions.assertThat(retrievedElement).isEqualTo(element);
    }

    @Test
    public void shouldUpdateInsertedElement() {
        Element element = new ElementImpl.Builder()
                .setAmount(1)
                .setHint("Hint")
                .setId("id")
                .setName("Name")
                .setSymbol("Symbol")
                .setUnitName("UnitName")
                .build();

        String recipeId = "recipe";
        String ingredientId = "asdf";

        Uri itemUri = contentResolver.insert(ElementContract.CONTENT_URI, ContentMapper.toContentValues(element, recipeId, ingredientId));

        final int newAmount = 4;

        ContentValues contentValues = new ContentValues();
        contentValues.put(ElementContract.Table.COLUMN_AMOUNT, newAmount);

        int updateCount = contentResolver.update(itemUri, contentValues, null, null);

        Cursor cursor = contentResolver.query(itemUri, null, null, null, null);

        Element retrievedElement = ContentMapper.toSingleElement(cursor);

        Assertions.assertThat(retrievedElement.getAmount()).isEqualTo(newAmount);
    }

    @Test
    public void shouldDeleteInsertedElement() {
        Element element = new ElementImpl.Builder()
                .setAmount(1)
                .setHint("Hint")
                .setId("id")
                .setName("Name")
                .setSymbol("Symbol")
                .setUnitName("UnitName")
                .build();

        String recipeId = "recipe";
        String ingredientId = "asdf";

        Uri itemUri = contentResolver.insert(ElementContract.CONTENT_URI, ContentMapper.toContentValues(element, recipeId, ingredientId));

        int deletionCount = contentResolver.delete(itemUri, null, null);

        Assertions.assertThat(deletionCount).isEqualTo(1);
    }
}
