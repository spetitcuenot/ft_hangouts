package com.example.ft_hangout.FtHangoutFragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.ft_hangout.Entity.Contacts;
import com.example.ft_hangout.FtHangoutAdapter.ContactsAdapter;
import com.example.ft_hangout.FtHangoutAdapter.CustomLayoutManager;
import com.example.ft_hangout.FtHangoutAdapter.RecyclerItemClickListener;
import com.example.ft_hangout.MainActivity;
import com.example.ft_hangout.R;
import com.example.ft_hangout.ThemesApplication.ThemeApplication;
import com.example.ft_hangout.Utils.Utils;
import com.example.ft_hangout.ViewModel.ContactsViewModel;
import com.example.ft_hangout.interfaces.OnContactListener;


import java.util.List;

import static com.example.ft_hangout.FtHangoutAdapter.RecyclerItemClickListener.*;


public class ContactsListFragment extends Fragment {

    private ContactsViewModel contactsViewModel;
    private RecyclerView recyclerView;
    private FragmentTransaction fragmentTransaction;
    private ContactsAdapter adapter;
    private Contacts _contact;
    private List<Contacts> _contacts;
    private ImageButton imgButton;
    private FloatingActionButton addContactButton;
    private int _position;

    public ContactsListFragment() {
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.contact_list_fragment, container, false);
        //Utils.onActivityCreateSetTheme(getActivity());
        recyclerView = view.findViewById(R.id.recycler_view);
        addContactButton = view.findViewById(R.id.button_add_contact);


        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactAddFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        if (adapter != null && _contacts != null)
        {
            adapter.setContacts(_contacts);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        recyclerView.setLayoutManager(new CustomLayoutManager(this.getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new ContactsAdapter(this.getContext(), _contacts, new OnContactListener() {
            @Override
            public void onContactSelected(Contacts contact) {
                contactsViewModel.getSelectedContact().setValue(contact);
                boolean expanded = contact.isExpanded();
                contact.setExpanded(!expanded);
            }
        });
        recyclerView.setAdapter(adapter);

        contactsViewModel = ViewModelProviders.of(getActivity()).get(ContactsViewModel.class);
        contactsViewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(@Nullable List<Contacts> contacts) {
                _contacts = contacts;
                adapter.setContacts(contacts);
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        //Utils.onActivityCreateSetTheme(getActivity());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_theme:


                Toast.makeText(this.getContext(), " Test Thème", Toast.LENGTH_LONG).show();
                return true;

            case R.id.theme_1:
                /*Utils.CUSTOM_ACTION_BAR_THEME =
                Utils.onActivityCreateSetTheme();*/
                _position = 0;
                Utils.changeToTheme(getActivity(), 0);
                ThemeApplication.currentPosition = _position;
                //setTheme(R.style.CustomActionBarThemeBlue);
                Toast.makeText(this.getContext(), "Thème 1", Toast.LENGTH_LONG).show();
                return true;

            case R.id.theme_2:
                Utils.changeToTheme(getActivity(), 1);
                _position = 1;
                //setTheme(R.style.CustomActionBarThemeOrange);
                //ThemeUtil.changeTheme(getContext());
                ThemeApplication.currentPosition = _position;
                Toast.makeText(this.getContext(), "Thème 2", Toast.LENGTH_LONG).show();
                return true;

                //editor.clear();//clears the editor to avoid errors
                /*editor.putInt("leTheme", newcheck);//add in new int
                editor.commit();//commit
                Toast.makeText(this.getContext(), "Relancer l'app pour charger le nouveau Thème", Toast.LENGTH_LONG).show();*/

                /*restart the activity
                Intent intent1 = new Intent(getActivity(), ContactsListFragment.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //Bundle intent1 = intent.getExtras();
                //Intent intnet1 = new Intent();

                //getActivity().finish();//close activity - avoid crash

                //ThemeUtil.changeTheme(getActivity());

                //startActivity(intent1);//start activity*/


                //TODO: Changement de Theme
                //break;

                /*boolean isDialogCall = getActivity().getIntent().equals(Intent.ACTION_PICK);
                setTheme(R.style.);
                return true;*/
            case R.id.action_language:
                Intent changerLangue = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(changerLangue);
                return true;

            case R.id.action_delete_All:
                contactsViewModel.deleteAllContacts();
                return true;

            case R.id.action_exit:
                getActivity().finish();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }


    private void setTheme(int textAppearance_appCompat) {
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
   /*     Activity activity = (Activity) context;
        {

        }
        if (context instanceof OnItemClickListener)
            try {
                OnItemClickListener onItemClickListener = (OnItemClickListener) activity;
            } catch (ClassCastException e) {
                throw new RuntimeException((context.toString() + " must implement list.."));
            }*/
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        OnItemClickListener onItemClickListener = null;
    }
}
