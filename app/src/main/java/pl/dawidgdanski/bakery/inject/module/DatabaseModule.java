package pl.dawidgdanski.bakery.inject.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.dawidgdanski.bakery.controller.PersistenceManager;
import pl.dawidgdanski.bakery.database.DatabaseHelper;
import pl.dawidgdanski.bakery.inject.Qualifiers;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public DatabaseHelper provideDatabaseHelper(Context context) {
        return new DatabaseHelper(context);
    }

    @Provides
    @Singleton
    @Named(Qualifiers.READABLE_DATABASE)
    public SQLiteDatabase provideReadableDatabase(DatabaseHelper databaseHelper) {
        return databaseHelper.getReadableDatabase();
    }

    @Provides
    @Singleton
    @Named(Qualifiers.WRITABLE_DATABASE)
    public SQLiteDatabase provideWritableDatabase(DatabaseHelper databaseHelper) {
        return databaseHelper.getWritableDatabase();
    }

    @Provides
    @Singleton
    public PersistenceManager providePersistenceManager(Context context) {
        return new PersistenceManager(context);
    }
}
