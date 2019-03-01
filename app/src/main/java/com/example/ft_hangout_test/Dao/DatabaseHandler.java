package com.example.ft_hangout_test.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static class Finals implements BaseColumns {
        protected final static String DATABASE_NAME = "Contactsbase.db";
        public static final String TABLE_NAME = "contacts";
        public static final String KEY = "id";
        public static final String LASTNAME = "lastname";
        public static final String FIRSTNAME = "firstname";
        public static final String NUMOBILE = "numobile";
        public static final String NUFIX = "nufix";
        public static final String MAIL = "mail";
        public static final String ADDRESS = "address";
        //public static final String PHOTO = "photo";
    }

    public static final String TABLE_CREATE = "CREATE TABLE "
            + Finals.TABLE_NAME + " ("
            + Finals.KEY + " INTEGER " + "PRIMARY KEY AUTOINCREMENT, "
            + Finals.LASTNAME + " TEXT" + " NOT NULL,"
            + Finals.FIRSTNAME + " TEXT, "
            + Finals.NUMOBILE + " INTEGER, "
            + Finals.NUFIX + " INTEGER, "
            + Finals.MAIL + " TEXT, "
            + Finals.ADDRESS + " TEXT);";

  /*  public static final String DATABASE_ALTER_CONTACTS = "ALTER TABLE"
            + Finals.TABLE_NAME + "ADD COLUMN" + PHOTO + "string;";*/

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + Finals.TABLE_NAME + ";";


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       // sqLiteDatabase.execSQL(Finals.TABLE_NAME);
        sqLiteDatabase.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
       /* Log.w("DBOpenHelper", "Mise à jour de la version " + oldVersion
                + " vers la version " + newVersion
                + ", les anciennes données seront détruites ");*/
        // Drop the old database
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Finals.TABLE_NAME);

        //sqLiteDatabase.execSQL(DATABASE_ALTER_CONTACTS);
        sqLiteDatabase.execSQL(TABLE_DROP);
        // Create the new one
        this.onCreate(sqLiteDatabase);

    }

}
