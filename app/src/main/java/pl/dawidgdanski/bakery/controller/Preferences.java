package pl.dawidgdanski.bakery.controller;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

final class Preferences {

    private final SharedPreferences sharedPreferences;

    private final Lock readLock;

    private final Lock writeLock;


    public Preferences(final String preferenceTarget, final Context applicationContext) {
        sharedPreferences = applicationContext.getSharedPreferences(preferenceTarget, Context.MODE_PRIVATE);

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    public void remove(final String key) {
        writeLock.lock();
        try {
            sharedPreferences.edit()
                    .remove(key)
                    .apply();
        } finally {
            writeLock.unlock();
        }
    }

    public void putString(final String key, final String value) {
        writeLock.lock();
        try {
            sharedPreferences.edit()
                    .putString(key, value)
                    .apply();
        } finally {
            writeLock.unlock();
        }
    }

    public String getString(final String key) {
        readLock.lock();
        try {
            return sharedPreferences.getString(key, "");
        } finally {
            readLock.unlock();
        }
    }

    public void putInt(final String key, final int value) {
        writeLock.lock();
        try {
            sharedPreferences.edit()
                    .putInt(key, value)
                    .apply();
        } finally {
            writeLock.unlock();
        }
    }

    public int getInt(final String key) {
        readLock.lock();

        try {
            return sharedPreferences.getInt(key, Integer.MIN_VALUE);
        } finally {
            readLock.unlock();
        }
    }

    public void putBoolean(final String key, final boolean value) {
        writeLock.lock();
        try {
            sharedPreferences.edit()
                    .putBoolean(key, value)
                    .apply();
        } finally {
            writeLock.unlock();
        }
    }

    public void putLong(final String key, final long value) {
        writeLock.lock();
        try {
            sharedPreferences.edit()
                    .putLong(key, value)
                    .apply();
        } finally {
            writeLock.unlock();
        }
    }

    public long getLong(final String key, final long defaultValue) {
        readLock.lock();
        try {
            return sharedPreferences.getLong(key, defaultValue);
        } finally {
            readLock.unlock();
        }
    }

    public boolean getBoolean(final String key) {
        readLock.lock();
        try {
            return sharedPreferences.getBoolean(key, false);
        } finally {
            readLock.unlock();
        }
    }

    public SharedPreferences.Editor edit() {
        return sharedPreferences.edit();
    }

    public void clear() {

        sharedPreferences.edit()
                .clear()
                .apply();
    }
}
