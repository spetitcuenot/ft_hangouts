package com.example.ft_hangout.DataBase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

import com.example.ft_hangout.Dao.ContactsDao;
import com.example.ft_hangout.Entity.Contacts;


@Database(entities = {Contacts.class}, version = 1, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {

    private static  ContactsDatabase instance;

    public  abstract ContactsDao contactsDao();

    public static synchronized ContactsDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ContactsDatabase.class, "contacts_database.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void  onCreate(SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();

        }

    };

    private static class  PopulateDBAsyncTask   extends AsyncTask<Void, Void, Void> {
        private ContactsDao contactsDao;

        private  PopulateDBAsyncTask(ContactsDatabase db){
            contactsDao = db.contactsDao();
        }
        @Override
        protected Void doInBackground(Void... voids){
            contactsDao.insert(new Contacts("@drawable/avatar101", "Chretien", "Hélene","","","",""));
            contactsDao.insert(new Contacts("@drawable/avatar101", "Petitcuénot", "Sylvain","0652414888","","",""));
            contactsDao.insert(new Contacts("@drawable/avatar101", "Selvais", "Frederic","","","",""));
            contactsDao.insert(new Contacts("@drawable/avatar101", "Gaillard", "Isabelle","","","",""));
            contactsDao.insert(new Contacts("@drawable/avatar101", "Gaillard", "Robin","","","",""));
            contactsDao.insert(new Contacts("@drawable/avatar101", "Martin", "Rémi","","","",""));
            contactsDao.insert(new Contacts("@drawable/avatar101", "Smith", "laurent","","","",""));
            contactsDao.insert(new Contacts("@drawable/avatar101", "Golden", "Marie","","","",""));
            contactsDao.insert(new Contacts("@drawable/avatar101", "Amaral", "Louis","","","",""));
            contactsDao.insert(new Contacts("@drawable/avatar101", "Durant", "Sylviane","","","",""));
            contactsDao.insert(new Contacts("@drawable/avatar101", "Chatron", "Isabelle","","","",""));
            contactsDao.insert(new Contacts("@drawable/avatar101", "Nery", "Fabrice","","","",""));
            return null;
        }
    }
}


