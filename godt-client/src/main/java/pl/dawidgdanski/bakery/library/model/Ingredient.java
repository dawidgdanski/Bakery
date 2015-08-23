package pl.dawidgdanski.bakery.library.model;

import java.util.Collection;

public interface Ingredient extends Model {

    String getId();

    String getName();

    Collection<Element> getElements();
}
