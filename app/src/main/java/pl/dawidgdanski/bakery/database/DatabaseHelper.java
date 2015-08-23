package pl.dawidgdanski.bakery.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.google.common.base.Preconditions;
import com.google.common.io.Closeables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import pl.dawidgdanski.bakery.R;
import pl.dawidgdanski.bakery.database.contract.Contracts;

public final class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper SINGLETON;

    public static final String DATABASE_NAME = "bakery_db.db";

    private static final String SEPARATOR = "#";

    private static final int DATABASE_VERSION = 1;

    private Context mContext;

    public static synchronized DatabaseHelper init(final Context context) {
        if (SINGLETON == null) {
            SINGLETON = new DatabaseHelper(context);
        }

        return SINGLETON;
    }

    public static synchronized DatabaseHelper getInstance() {
        Preconditions.checkNotNull(SINGLETON, "DatabaseHelper not initialized!");
        return SINGLETON;
    }

    public static void release() {
        SINGLETON = null;
    }

    private DatabaseHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        final String[] queries = getQueriesArray();

        for (final String query : queries) {
            database.execSQL(query.trim());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        for (final Uri resourceUri : Contracts.getResourceUris()) {
            String statement = String.format("DROP TABLE IF EXISTS %s", resourceUri.getLastPathSegment());
            database.execSQL(statement);
        }
        onCreate(database);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private InputStream openDatabaseMetaResource() {
        return mContext.getResources().openRawResource(R.raw.bakery_database);
    }

    private String[] getQueriesArray() {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openDatabaseMetaResource()));
        String line;
        final StringBuilder queryBuilder = new StringBuilder();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                queryBuilder.append(line);
            }
            final String databaseString = queryBuilder.toString();

            return databaseString.split(SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(getClass().getSimpleName() + ": cannot create database!");
        } finally {
            Closeables.closeQuietly(bufferedReader);
        }
    }


}
