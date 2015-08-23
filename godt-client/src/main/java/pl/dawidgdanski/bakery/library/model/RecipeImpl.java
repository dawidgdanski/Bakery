package pl.dawidgdanski.bakery.library.model;

import android.os.Parcel;
import android.text.TextUtils;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RecipeImpl extends AbstractModel implements Recipe {

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        public Recipe createFromParcel(Parcel source) {

            int databaseId = source.readInt();

            String title = source.readString();
            String description = source.readString();
            String imageUrl = source.readString();

            int ingredientsSize = source.readInt();
            List<Ingredient> ingredients = new ArrayList<Ingredient>(ingredientsSize);
            source.readTypedList(ingredients, IngredientImpl.CREATOR);

            return new Builder()
                    .setDatabaseId(databaseId)
                    .setTitle(title)
                    .setDescription(description)
                    .addIngredients(ingredients)
                    .setImageUrl(imageUrl)
                    .build();
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    private final String id;

    private final String title;

    private final String description;

    private final String imageUrl;

    private final List<Ingredient> ingredients;

    private final int hashCode;

    private RecipeImpl(Builder builder) {
        super(builder.databaseId);

        this.id = builder.id;

        this.title = builder.title;

        this.description = builder.description;

        this.imageUrl = builder.imageUrl;

        this.ingredients = Collections.unmodifiableList(new ArrayList<Ingredient>(builder.ingredients));

        this.hashCode = new HashCodeBuilder()
                .append(id)
                .append(title)
                .append(description)
                .append(ingredients.toArray())
                .toHashCode();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(o == null || !(o instanceof RecipeImpl)) {
            return false;
        }

        RecipeImpl rhs = (RecipeImpl) o;

        for(Ingredient ingredient : rhs.ingredients) {
            if(! this.ingredients.contains(ingredient)) {
                return false;
            }
        }

        return TextUtils.equals(this.title, rhs.title) &&
                TextUtils.equals(this.description, rhs.description) &&
                TextUtils.equals(this.id, rhs.id) &&
                TextUtils.equals(this.imageUrl, rhs.imageUrl);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(getDatabaseId());

        dest.writeString(this.title);

        dest.writeString(this.description);

        dest.writeString(this.imageUrl);

        dest.writeInt(ingredients.size());

        dest.writeTypedList(ingredients);
    }

    public static class Builder {

        private int databaseId;

        private String id;

        private String title;

        private String description;

        private String imageUrl;

        private List<Ingredient> ingredients = new ArrayList<Ingredient>();

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setImageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder addIngredients(Collection<Ingredient> ingredients) {
            this.ingredients.addAll(ingredients);
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setDatabaseId(int databaseId) {
            this.databaseId = databaseId;
            return this;
        }

        public RecipeImpl build() {
            return new RecipeImpl(this);
        }
    }
}
