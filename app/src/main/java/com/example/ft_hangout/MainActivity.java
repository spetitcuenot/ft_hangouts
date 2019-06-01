package com.example.ft_hangout;


import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.ft_hangout.Entity.Contacts;
import com.example.ft_hangout.FtHangoutFragment.ContactAddFragment;
import com.example.ft_hangout.FtHangoutFragment.ContactDetailsFragment;
import com.example.ft_hangout.FtHangoutFragment.ContactsListFragment;
import com.example.ft_hangout.AppConstant;
import com.example.ft_hangout.Utils.Utils;
import com.example.ft_hangout.ViewModel.ContactsViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

import static com.example.ft_hangout.R.id.fragment_container;
import static com.example.ft_hangout.R.id.labeled;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ContactsListFragment listFragment;
    private FragmentTransaction fragmentTransaction;
    private ImageButton imgButton;
    private ContactsViewModel _contactsViewModel;
    private Contacts _contact;
    private OwnerAccount ownerAccount;
    private String dateTime;
    private static final String DATETIME = "dateTime";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (findViewById(fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
        }
        listFragment = new ContactsListFragment();
        listFragment.getLifecycle();
        _contactsViewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listFragment, null).commit();

        sharedPreferences = getBaseContext().getSharedPreferences(DATETIME, MODE_PRIVATE);

    }

    @Override
    public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityFromFragment(fragment, intent, requestCode, options);
    }


    public void onClick(View v) {
        imgButton = (ImageButton) v;
        switch (imgButton.getId()) {
            case R.id.details:
                fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactDetailsFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.sms:
                _contact = _contactsViewModel.getSelectedContact().getValue();
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                if( _contact.getNumobile() != null)
                {
                    smsIntent.putExtra("address",_contact.getNumobile());
                }
                else
                {
                    smsIntent.putExtra("address","");
                }
                smsIntent.putExtra("sms_body","Body of Message");
                startActivity(smsIntent);


                //Toast.makeText(this, "Bouton SMS clické !", Toast.LENGTH_LONG).show();

                break;
            /*case R.id.phone:
                _contact = _contactsViewModel.getSelectedContact().getValue();
                Intent phoneIntent = new Intent(Intent.ACTION_VIEW);
                phoneIntent.setType("vnd.android-dir/mms-sms");
                if( _contact.getNumobile() != null)
                {
                    phoneIntent.putExtra("address",_contact.getNumobile());
                }
                else
                {
                    phoneIntent.putExtra("address","");
                }
                phoneIntent.putExtra("sms_body","Body of Message");
                startActivity(phoneIntent);*/
            default:
                return;
        }
    }

    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      /* if (listFragment != null) {
            // somehow the event doesn't get dispatched correctly
            listFragment.onActivityResult(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }*/
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onStart() {
        super.onStart();
        getDelegate().onStart();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, dateTime, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dateTime = getCurrentTimeStamp();
        //Toast.makeText(this, dateTime, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        getDelegate().onStop();
        //dateTime = getCurrentTimeStamp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


}




 /*   @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.clone();
        detailsFragment.setArguments(bundle);
    }*/

    /*@Override
    public void onItemClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.clone();
        detailsFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailsFragment, null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }*/

  /*  @Override
    public void onClick(View view) {

    }*/

   /* @Override
    public void onItemClick(View view, int pos) {

        ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment();
        Bundle bundle = new Bundle();
       // bundle.put
        contactDetailsFragment.setArguments(bundle);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, contactDetailsFragment, null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }*/





    /*@Override
    public void onClickContact(Contacts contacts) {

    }*/


//  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailsFragment).commit();

       /* RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ContactsAdapter adapter = new ContactsAdapter(this.getApplicationContext());
        recyclerView.setAdapter(adapter);

        contactsViewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        contactsViewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(@Nullable List<Contacts> contacts) {
                adapter.setContacts(contacts);

            }
        });*/




