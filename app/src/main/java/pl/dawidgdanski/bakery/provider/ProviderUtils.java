package pl.dawidgdanski.bakery.provider;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.text.TextUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import java.util.Collection;

final class ProviderUtils {

    private ProviderUtils() { }

    static void notifyChange(ContentResolver contentResolover,
                             Collection<Uri> uris,
                             ContentObserver observer,
                             boolean syncToNetwork) {
        for(Uri uri : uris) {
            contentResolover.notifyChange(uri, observer, syncToNetwork);
        }
    }

    static String whereEqualTo(final String name, final String value) {
        Preconditions.checkNotNull(name, "parameter name is null");
        Preconditions.checkNotNull(value, "parameter value is null");

        return Joiner.on(" ").join(name, "=", value);
    }

    static String withOptionalSelection(final String selection) {
        return !TextUtils.isEmpty(selection) ? " AND (" + selection + " )" : "";
    }

    static String getRowId(final Uri uri) {
        Preconditions.checkNotNull(uri, "Uri is null");

        return uri.getPathSegments().get(1);
    }

    static Cursor returnSameOrEmptyCursor(final Cursor cursor) {
        return cursor == null ? new EmptyCursor() : cursor;
    }

    private static class EmptyCursor extends MatrixCursor {

        public EmptyCursor() {
            super(new String[]{}, 1);
        }

        @Override
        public int getColumnIndexOrThrow(String columnName) {
            return -1;
        }

        @Override
        public int getColumnIndex(String columnName) {
            return -1;
        }
    }
}
