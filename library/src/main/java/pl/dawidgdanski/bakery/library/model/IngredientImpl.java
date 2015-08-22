package pl.dawidgdanski.bakery.library.model;

import android.os.Parcel;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class IngredientImpl implements Ingredient {

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        public Ingredient createFromParcel(Parcel source) {

            String id = source.readString();

            String name = source.readString();

            int amount = source.readInt();

            String hint = source.readString();

            String unitName = source.readString();

            String symbol = source.readString();

            MenuCategory menuCategory = source.readParcelable(MenuCategory.class.getClassLoader());

            return new Builder()
                    .setId(id)
                    .setName(name)
                    .setAmount(amount)
                    .setHint(hint)
                    .setUnitName(unitName)
                    .setSymbol(symbol)
                    .setMenuCategory(menuCategory)
                    .build();
        }

        public Ingredient[] newArray(int size) {
            return new IngredientImpl[size];
        }
    };

    private final String id;

    private final String name;

    private final int amount;

    private final String hint;

    private final String unitName;

    private final String symbol;

    private final MenuCategory menuCategory;

    private final int hashCode;

    private IngredientImpl(Builder builder) {

        this.id = builder.id;

        this.name = builder.name;

        this.amount = builder.amount;

        this.hint = builder.hint;

        this.unitName = builder.unitName;

        this.symbol = builder.symbol;

        this.menuCategory = builder.menuCategory;

        this.hashCode = new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(amount)
                .append(hint)
                .append(unitName)
                .append(symbol)
                .append(menuCategory)
                .toHashCode();
    }

    @Nullable
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Nullable
    @Override
    public String getHint() {
        return hint;
    }

    @Override
    public String getUnitName() {
        return unitName;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || !(o instanceof IngredientImpl)) {
            return false;
        }

        IngredientImpl rhs = (IngredientImpl) o;

        return TextUtils.equals(this.id, rhs.id) &&

                TextUtils.equals(this.name, rhs.name) &&

                TextUtils.equals(this.hint, rhs.hint) &&

                TextUtils.equals(this.unitName, rhs.unitName) &&

                TextUtils.equals(this.symbol, rhs.symbol) &&

                this.amount == rhs.amount &&

                this.menuCategory.equals(rhs.menuCategory);
    }

    @Override
    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    public static class Builder {
        private String id;

        private String name;

        private int amount;

        private String hint;

        private String unitName;

        private String symbol;

        private MenuCategory menuCategory;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder setHint(String hint) {
            this.hint = hint;
            return this;
        }

        public Builder setUnitName(String unitName) {
            this.unitName = unitName;
            return this;
        }

        public Builder setSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder setMenuCategory(MenuCategory menuCategory) {
            this.menuCategory = menuCategory;
            return this;
        }

        public IngredientImpl build() {
            return new IngredientImpl(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.id);

        dest.writeString(this.name);

        dest.writeInt(this.amount);

        dest.writeString(this.hint);

        dest.writeString(this.unitName);

        dest.writeString(this.symbol);

        dest.writeParcelable(this.menuCategory, 0);
    }
}
