package pl.dawidgdanski.bakery.library.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class ElementImpl extends AbstractModel implements Element {

    public static final Parcelable.Creator<Element> CREATOR = new Parcelable.Creator<Element>() {
        public Element createFromParcel(Parcel source) {

            int databaseId = source.readInt();

            String id = source.readString();

            String name = source.readString();

            int amount = source.readInt();

            String hint = source.readString();

            String unitName = source.readString();

            String symbol = source.readString();

            return new Builder()
                    .setDatabaseId(databaseId)
                    .setId(id)
                    .setName(name)
                    .setAmount(amount)
                    .setHint(hint)
                    .setUnitName(unitName)
                    .setSymbol(symbol)
                    .build();
        }

        public Element[] newArray(int size) {
            return new Element[size];
        }
    };

    private final String id;

    private final String name;

    private final int amount;

    private final String hint;

    private final String unitName;

    private final String symbol;

    private final int hashCode;

    private ElementImpl(Builder builder) {
        super(builder.databaseId);

        this.id = builder.id;

        this.name = builder.name;

        this.amount = builder.amount;

        this.hint = builder.hint;

        this.unitName = builder.unitName;

        this.symbol = builder.symbol;

        this.hashCode = new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(amount)
                .append(hint)
                .append(unitName)
                .append(symbol)
                .toHashCode();
    }

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

        if (o == null || !(o instanceof ElementImpl)) {
            return false;
        }

        ElementImpl rhs = (ElementImpl) o;

        return TextUtils.equals(this.id, rhs.id) &&

                TextUtils.equals(this.name, rhs.name) &&

                TextUtils.equals(this.hint, rhs.hint) &&

                TextUtils.equals(this.unitName, rhs.unitName) &&

                TextUtils.equals(this.symbol, rhs.symbol) &&

                this.amount == rhs.amount;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }


    public static class Builder {

        private int databaseId;

        private String id;

        private String name;

        private int amount;

        private String hint;

        private String unitName;

        private String symbol;

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

        public Builder setDatabaseId(int databaseId) {
            this.databaseId = databaseId;
            return this;
        }

        public ElementImpl build() {
            return new ElementImpl(this);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(getDatabaseId());

        dest.writeString(this.id);

        dest.writeString(this.name);

        dest.writeInt(this.amount);

        dest.writeString(this.hint);

        dest.writeString(this.unitName);

        dest.writeString(this.symbol);
    }
}
