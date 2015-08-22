package pl.dawidgdanski.bakery.library.model;

import android.os.Parcel;
import android.text.TextUtils;

import org.apache.commons.lang.builder.HashCodeBuilder;

public class MenuCategoryImpl implements MenuCategory {

    private final String name;

    private final String id;

    private final int hashCode;

    public MenuCategoryImpl(String name, String id) {

        this.name = name;

        this.id = id;

        this.hashCode = new HashCodeBuilder()
                .append(name)
                .append(id)
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
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        if(o == null || !(o instanceof MenuCategory)) {
            return false;
        }

        MenuCategoryImpl rhs = (MenuCategoryImpl) o;

        return TextUtils.equals(this.id, rhs.id) &&
                TextUtils.equals(this.name, rhs.name);

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

        dest.writeString(this.name);

        dest.writeString(this.id);
    }

    public static final Creator<MenuCategory> CREATOR = new Creator<MenuCategory>() {
        public MenuCategory createFromParcel(Parcel source) {
            String name = source.readString();
            String id = source.readString();

            return new MenuCategoryImpl(name, id);
        }

        public MenuCategory[] newArray(int size) {
            return new MenuCategory[size];
        }
    };
}
