package pl.dawidgdanski.bakery.provider;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;

import pl.dawidgdanski.bakery.AppRobolectricTestRunner;
import pl.dawidgdanski.bakery.database.ContentMapper;
import pl.dawidgdanski.bakery.database.contract.RecipeContract;
import pl.dawidgdanski.bakery.library.model.Recipe;
import pl.dawidgdanski.bakery.library.model.RecipeImpl;

@RunWith(AppRobolectricTestRunner.class)
public class AppContentProviderTest {

    private final ContentResolver contentResolver;

    public AppContentProviderTest() {
        this.contentResolver = RuntimeEnvironment.application.getContentResolver();
    }


    @Test
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
/*
    @Test
    public void shouldUpdateInsertedRecipe() {

    }

    @Test
    public void shouldDeleteInsertedRecipe() {

    }

    //Ingredient

    @Test
    public void shouldInsertAndRetrieveIngredient() {

    }

    @Test
    public void shouldUpdateInsertedIngredient() {

    }

    @Test
    public void shouldDeleteInsertedIngredient() {

    }

    //Elements
    @Test
    public void shouldInsertAndRetrieveElement() {

    }

    @Test
    public void shouldUpdateInsertedElement() {

    }

    @Test
    public void shouldDeleteInsertedElement() {

    }*/
}
