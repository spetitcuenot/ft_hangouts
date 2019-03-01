package com.example.ft_hangout_test;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ft_hangout_test.Dao.Contacts;
import com.example.ft_hangout_test.Dao.ContactsDAO;
import com.example.ft_hangout_test.Dao.DAOBddsqlite;
import com.example.ft_hangout_test.Dao.DatabaseHandler;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ContactsDAO db;
    Contacts contact;
    Contacts contact1;
    String firtsname;
    String lastname;
    //ArrayAdapter<Contacts> listadapter = null;



    //DatabaseHandler mDbHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firtsname = "Petitcuenot";
        lastname = "Sylvain";
        final ListView listContView = (ListView) findViewById(R.id.listContactView);

        db = new ContactsDAO(this.getApplicationContext());
        db.open();
        db.getDb();

        contact = new Contacts();
        //contact1 = new Contacts();
        contact.setFirstname(firtsname);
        contact.setLastname(lastname);
        //firtsname = "Chretien";
       // lastname = "Helene";
       // contact1.setFirstname(firtsname);
      //  contact1.setLastname(lastname);
        db.addNewContact(contact);
    //    db.addNewContat(contact1);

        Thread chrono = new Thread() {
            public void run () {
                try {
                    sleep(5000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent();
                    startActivity(i);
                }

            }

        };
        chrono.start();





       /* ArrayList<Contacts> listContac = new ArrayList<Contacts>();
        for (int i = 0; i < db.contactsList().size(); i++) {

            listContac.add(db.contactsList().get(i));
        }
        ;*/
        ArrayList<Contacts> listContac = new ArrayList<Contacts>(db.contactsList());
        //ArrayList<Contacts> listContac = new ArrayList<Contacts>(db.contactsList().indexOf(contact1.getId()));
        final ArrayAdapter<Contacts> listadapter = new ArrayAdapter<Contacts>(this, android.R.layout.simple_list_item_activated_1, listContac);
        //listadapter = new ArrayAdapter<Contacts>(this, android.R.layout.simple_list_item_activated_1,listContac );
        listadapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        listContView.setAdapter(listadapter);

        db.close();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
