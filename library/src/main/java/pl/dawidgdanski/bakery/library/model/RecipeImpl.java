package pl.dawidgdanski.bakery.library.model;

import android.os.Parcel;
import android.text.TextUtils;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RecipeImpl implements Recipe {

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        public Recipe createFromParcel(Parcel source) {

            String title = source.readString();
            String description = source.readString();

            int ingredientsSize = source.readInt();
            List<Ingredient> ingredients = new ArrayList<Ingredient>(ingredientsSize);
            source.readTypedList(ingredients, IngredientImpl.CREATOR);

            int stepsSize = source.readInt();
            List<Step> steps = new ArrayList<Step>(stepsSize);
            source.readTypedList(steps, StepImpl.CREATOR);

            return new Builder()
                    .setTitle(title)
                    .setDescription(description)
                    .addIngredients(ingredients)
                    .addSteps(steps)
                    .build();
        }

        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    private final String id;

    private final String title;

    private final String description;

    private final List<Ingredient> ingredients;

    private final List<Step> steps;

    private final int hashCode;

    private RecipeImpl(Builder builder) {

        this.id = builder.id;

        this.title = builder.title;

        this.description = builder.description;

        this.ingredients = Collections.unmodifiableList(new ArrayList<Ingredient>(builder.ingredients));

        this.steps = Collections.unmodifiableList(new ArrayList<Step>(builder.steps));

        this.hashCode = new HashCodeBuilder()
                .append(id)
                .append(title)
                .append(description)
                .append(steps.toArray())
                .append(ingredients.toArray())
                .toHashCode();
    }

    @Override
    public String getId() {
        return null;
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
    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public Collection<Step> getSteps() {
        return steps;
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

        for(Step step : rhs.steps) {
            if(! this.steps.contains(step)) {
                return false;
            }
        }

        for(Ingredient ingredient : rhs.ingredients) {
            if(! this.ingredients.contains(ingredient)) {
                return false;
            }
        }

        return TextUtils.equals(this.title, rhs.title) &&
                TextUtils.equals(this.description, rhs.description) &&
                TextUtils.equals(this.id, rhs.id);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.title);

        dest.writeString(this.description);

        dest.writeInt(ingredients.size());

        dest.writeTypedList(ingredients);

        dest.writeInt(steps.size());

        dest.writeTypedList(steps);
    }

    public static class Builder {

        private String id;

        private String title;

        private String description;

        private List<Ingredient> ingredients = new ArrayList<Ingredient>();

        private List<Step> steps = new ArrayList<Step>();

        public Builder setTitle(String title) {
            this.title = title;
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

        public Builder addSteps(Collection<Step> steps) {
            this.steps.addAll(steps);
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public RecipeImpl build() {
            return new RecipeImpl(this);
        }
    }
}
