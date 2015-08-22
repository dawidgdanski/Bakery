package pl.dawidgdanski.bakery.library.model;

import android.os.Parcel;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class StepImpl implements Step {

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        public Step createFromParcel(Parcel source) {
            String description = source.readString();
            String id = source.readString();
            String heading = source.readString();

            return new Builder()
                    .setDescription(description)
                    .setId(id)
                    .setHeading(heading)
                    .build();
        }

        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    private final String description;

    private final String id;

    private final String heading;

    private final int hashCode;

    private StepImpl(Builder builder) {

        this.description = builder.description;

        this.id = builder.id;

        this.heading = builder.heading;

        this.hashCode = new HashCodeBuilder()
                .append(description)
                .append(id)
                .append(heading)
                .toHashCode();
    }

    @Override
    public String getId() {
        return id;
    }

    @Nullable
    @Override
    public String getHeading() {
        return heading;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || !(obj instanceof StepImpl)) {
            return false;
        }

        StepImpl rhs = (StepImpl) obj;

        return TextUtils.equals(this.id, rhs.id) &&
                TextUtils.equals(this.description , rhs.description) &&
                TextUtils.equals(this.heading, rhs.heading);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    public static class Builder {
        private String description;
        private String id;

        private String heading;

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setHeading(String heading) {
            this.heading = heading;
            return this;
        }

        public StepImpl build() {
            return new StepImpl(this);
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.description);

        dest.writeString(this.id);

        dest.writeString(this.heading);
    }
}
