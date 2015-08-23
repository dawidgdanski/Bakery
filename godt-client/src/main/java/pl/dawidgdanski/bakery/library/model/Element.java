package pl.dawidgdanski.bakery.library.model;

public interface Element extends Model {

    String getId();

    String getName();

    int getAmount();

    String getHint();

    String getUnitName();

    String getSymbol();
}
