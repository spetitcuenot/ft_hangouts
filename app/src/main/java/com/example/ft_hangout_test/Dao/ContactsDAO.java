package com.example.ft_hangout_test.Dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class ContactsDAO extends DAOBddsqlite {
    public ContactsDAO(Context pContext) {
        super(pContext);
    }

    public static final String TABLE_NAME = "contacts";
    public static final String KEY = "id";
    public static final String LASTNAME = "lastname";
    public static final String FIRSTNAME = "firstname";
    public static final String NUMOBILE = "numobile";
    public static final String NUFIX = "nufix";
    public static final String MAIL = "mail";
    public static final String ADDRESS = "address";

    //private static ContactsDAO sInstance;
    //private static Map allConstacts = new HashMap();
    //protected DatabaseHandler mHandler = null;


    public static final String TABLE_CREATE = "CREATE TABLE "
            + TABLE_NAME + " ("
            + KEY + " INTEGER " + "PRIMARY KEY AUTOINCREMENT, "
            + LASTNAME + " TEXT, "
            + FIRSTNAME + " TEXT, "
            + NUMOBILE + " INTEGER, "
            + NUFIX + " INTEGER, "
            + MAIL + " TEXT, "
            + ADDRESS + " TEXT);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + DatabaseHandler.Finals.TABLE_NAME + ";";

    public void addNewContact(Contacts c) {
        ContentValues value = new ContentValues();
        value.put(ContactsDAO.LASTNAME, c.getLastname());
        value.put(ContactsDAO.FIRSTNAME, c.getFirstname());
        value.put(ContactsDAO.NUMOBILE, c.getNumobile());
        value.put(ContactsDAO.NUFIX, c.getNufix());
        value.put(ContactsDAO.MAIL, c.getMail());
        value.put(ContactsDAO.ADDRESS, c.getAddress());

        mDb.insert(ContactsDAO.TABLE_NAME, null, value);

    }


    public void updateContact(Contacts c) {

        ContentValues value = new ContentValues();
        value.put(ContactsDAO.KEY, c.getId());
        value.put(ContactsDAO.LASTNAME, c.getLastname());
        value.put(ContactsDAO.FIRSTNAME, c.getFirstname());
        value.put(ContactsDAO.NUMOBILE, c.getNumobile());
        value.put(ContactsDAO.NUFIX, c.getNufix());
        value.put(ContactsDAO.MAIL, c.getMail());
        value.put(ContactsDAO.ADDRESS, c.getAddress());

        mDb.update(ContactsDAO.TABLE_NAME, value, KEY + " = ? ", new  String[] {String.valueOf(c.getId())});

    }
    public void delContat(long id) {

        mDb.delete(ContactsDAO.TABLE_NAME, KEY + " = ? ", new String[] {String.valueOf(id)});

    }


    public ArrayList<Contacts> contactsList(){


        Contacts cont = null;

        ArrayList<Contacts> allConstacts = new ArrayList<Contacts>();

        Cursor cursor = mDb.rawQuery("select id, lastname, firstname  from " + ContactsDAO.TABLE_NAME + ";", null);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            cont = new Contacts(0, null, null);
            cont.setId(cursor.getLong(0));
            cont.setLastname(cursor.getString(1));
            cont.setFirstname(cursor.getString(2));
            allConstacts.add(cont);
        }
        cursor.close();
        return allConstacts;

    }

  /*  public static final List<Contacts> ITEMS = new ArrayList<Contacts>();

    public static final Map<String, Contacts> ITEM_MAP = new HashMap<String, Contacts>();

    static {
                for ()



    }*/


        public Contacts selectContact(long id) {
        Cursor cursor = mDb.rawQuery("select * from " + ContactsDAO.TABLE_NAME + " where " + ContactsDAO.KEY + " = ? ", new String[]{String.valueOf(id)});
        // Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME, new String[] {});

        Contacts cont = new Contacts(0, null, null, 0, 0, null, null);

            cursor.moveToFirst();
            cont.setId(cursor.getLong(0));
            cont.setLastname(cursor.getString(1));
            cont.setFirstname(cursor.getString(2));
            cont.setNumobile(cursor.getInt(3));
            cont.setNufix(cursor.getInt(4));
            cont.setMail(cursor.getString(5));
            cont.setAddress(cursor.getString(6));

            cursor.close();

        return cont;

    }
  /*  public static synchronized ContactsDAO getInstance(Context context) {
        if (sInstance == null)  sInstance = new ContactsDAO(context);

        return sInstance;
    }*/
}


