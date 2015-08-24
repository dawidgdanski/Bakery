package pl.dawidgdanski.bakery.controller;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.RemoteException;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;

import pl.dawidgdanski.bakery.database.ContentMapper;
import pl.dawidgdanski.bakery.database.contract.ElementContract;
import pl.dawidgdanski.bakery.database.contract.IngredientContract;
import pl.dawidgdanski.bakery.database.contract.RecipeContract;
import pl.dawidgdanski.bakery.library.model.Element;
import pl.dawidgdanski.bakery.library.model.Ingredient;
import pl.dawidgdanski.bakery.library.model.Recipe;
import pl.dawidgdanski.bakery.provider.AppContentProvider;

public final class PersistenceManager {

    private final ContentResolver contentResolver;

    public PersistenceManager(Context context) {
        Preconditions.checkNotNull(context);
        this.contentResolver = context.getApplicationContext().getContentResolver();
    }

    public void persistRecipes(final Collection<Recipe> recipes) throws RemoteException, OperationApplicationException {

        ArrayList<ContentProviderOperation> operations = Lists.newArrayList();

        for (Recipe recipe : recipes) {
            operations.add(ContentProviderOperation.newInsert(
                    RecipeContract.CONTENT_URI)
                    .withValues(ContentMapper.toContentValues(recipe))
                    .build());

            String recipeId = recipe.getId();

            Collection<Ingredient> ingredients = recipe.getIngredients();

            for(Ingredient ingredient : ingredients) {
                operations.add(ContentProviderOperation.newInsert(
                        IngredientContract.CONTENT_URI)
                        .withValues(ContentMapper.toContentValues(ingredient, recipeId))
                        .build());

                String ingredientId = ingredient.getId();

                Collection<Element> elements = ingredient.getElements();

                for(Element element : elements) {
                    operations.add(ContentProviderOperation.newInsert(
                            ElementContract.CONTENT_URI)
                            .withValues(ContentMapper.toContentValues(element, ingredientId))
                            .build());
                }
            }
        }

        contentResolver.applyBatch(AppContentProvider.AUTHORITY, operations);
    }

}
