package pl.dawidgdanski.bakery.library.model;

import android.os.Parcel;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class IngredientImpl implements Ingredient {

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {

        public Ingredient createFromParcel(Parcel source) {

            String id = source.readString();

            String name = source.readString();

            int elementsSize = source.readInt();

            List<Element> elements = new ArrayList<Element>(elementsSize);

            source.readTypedList(elements, ElementImpl.CREATOR);

            return new Builder()
                    .setId(id)
                    .setName(name)
                    .addElements(elements)
                    .build();
        }

        public Ingredient[] newArray(int size) {
            return new IngredientImpl[size];
        }
    };

    private final String id;

    private final String name;

    private final List<Element> elements;

    private final int hashCode;

    private IngredientImpl(Builder builder) {

        this.id = builder.id;

        this.name = builder.name;


        this.elements = Collections.unmodifiableList(new ArrayList<Element>(builder.elements));

        this.hashCode = new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(elements.toArray())
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
    public Collection<Element> getElements() {
        return elements;
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

        if(rhs.elements.size() != this.elements.size()) {
            return false;
        }

        for(Element element : rhs.elements) {
            if(! this.elements.contains(element)) {
                return false;
            }
        }

        return TextUtils.equals(this.id, rhs.id) &&

                TextUtils.equals(this.name, rhs.name);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    public static class Builder {
        private String id;

        private String name;

        private List<Element> elements = new ArrayList<Element>();

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder addElements(Collection<Element> elements) {
            this.elements.addAll(elements);
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

        dest.writeInt(this.elements.size());

        dest.writeTypedList(this.elements);
    }
}
