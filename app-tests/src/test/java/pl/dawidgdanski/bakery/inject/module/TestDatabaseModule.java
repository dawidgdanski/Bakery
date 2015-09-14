package pl.dawidgdanski.bakery.inject.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import pl.dawidgdanski.bakery.controller.PersistenceManager;
import pl.dawidgdanski.bakery.database.DatabaseHelper;

import static org.mockito.Mockito.spy;

public class TestDatabaseModule extends DatabaseModule {

    @Override
    public DatabaseHelper provideDatabaseHelper(Context context) {
        return spy(super.provideDatabaseHelper(context));
    }

    @Override
    public SQLiteDatabase provideReadableDatabase(DatabaseHelper databaseHelper) {
        return spy(super.provideReadableDatabase(databaseHelper));
    }

    @Override
    public SQLiteDatabase provideWritableDatabase(DatabaseHelper databaseHelper) {
        return spy(super.provideWritableDatabase(databaseHelper));
    }

    @Override
    public PersistenceManager providePersistenceManager(Context context) {
        return spy(super.providePersistenceManager(context));
    }
}
