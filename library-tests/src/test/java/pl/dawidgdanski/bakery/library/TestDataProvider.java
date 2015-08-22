package pl.dawidgdanski.bakery.library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import pl.dawidgdanski.bakery.library.model.Element;
import pl.dawidgdanski.bakery.library.model.ElementImpl;
import pl.dawidgdanski.bakery.library.model.Ingredient;
import pl.dawidgdanski.bakery.library.model.IngredientImpl;
import pl.dawidgdanski.bakery.library.model.MenuCategoryImpl;
import pl.dawidgdanski.bakery.library.model.Step;
import pl.dawidgdanski.bakery.library.model.StepImpl;

public final class TestDataProvider {

    private TestDataProvider() {

    }

    public static Collection<Ingredient> generateNextIngredients(final int count) {

        Collection<Ingredient> ingredients = new ArrayList<Ingredient>(count);

        for (int i = 0; i < count; i++) {
            ingredients.add(new IngredientImpl.Builder()
                    .setId("_" + i)
                    .setName("name_" + i)
                    .addElements(Collections.<Element>singleton(new ElementImpl.Builder()
                            .setAmount(i)
                            .setId("id" + i)
                            .setName("name" + i)
                            .setHint("hint" + i)
                            .setSymbol("symbol" + i)
                            .setUnitName("nm" + i)
                            .setMenuCategory(new MenuCategoryImpl("name" + i, "id" + i))
                            .build()))
                    .build());
        }

        return Collections.unmodifiableCollection(ingredients);
    }

    public static Collection<Step> generateNextSteps(final int count) {

        Collection<Step> steps = new ArrayList<Step>(count);

        for (int i = 0; i < count; i++) {
            steps.add(new StepImpl.Builder()
                    .setId("_" + i)
                    .setHeading("Heading" + i)
                    .setDescription("Description" + i)
                    .build());
        }

        return Collections.unmodifiableCollection(steps);
    }
}
