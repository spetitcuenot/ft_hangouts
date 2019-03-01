package com.example.ft_hangout_test.Dao;

import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase;
import  android.content.Context;


public abstract class DAOBddsqlite {

    protected final static int VERSION = 1;

    protected final static String DATABASE_NAME = "Contactsbase.db";

    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler = null;

    public DAOBddsqlite(Context pContext) {
        this.mHandler = new DatabaseHandler(pContext, DATABASE_NAME, null, VERSION);
    }

    public void  open() throws SQLiteException {
        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        try {
            mDb = mHandler.getWritableDatabase();
        } catch (SQLiteException ex) {
            mDb = mHandler.getReadableDatabase();
        }
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;

    }
}
