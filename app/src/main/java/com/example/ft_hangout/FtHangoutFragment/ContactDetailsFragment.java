package com.example.ft_hangout.FtHangoutFragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.arch.lifecycle.ViewModelProviders;

import com.example.ft_hangout.CircularImageView;
import com.example.ft_hangout.Entity.Contacts;
import com.example.ft_hangout.R;
import com.example.ft_hangout.ViewModel.ContactsViewModel;


public class ContactDetailsFragment extends Fragment {

    private ContactsViewModel contactsViewModel;
    private FragmentTransaction fragmentTransaction;
    private static Contacts contact;
    private CircularImageView _avatar;
    private TextView _lastname;
    private TextView _firstname;
    private TextView _numobile;
    private TextView _nufix;
    private TextView _mail;
    private TextView _address;
    private TextView _fullname;


    public ContactDetailsFragment() {
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_details_fragment, container, false);


        _avatar = view.findViewById(R.id.user_profile_photo);
        _fullname = view.findViewById(R.id.user_profile_name);
        _lastname = view.findViewById(R.id.lastname_Detail);
        _firstname = view.findViewById(R.id.firstname_Detail);
        _numobile = view.findViewById(R.id.mobile_Detail);
        _nufix = view.findViewById(R.id.fixe_Detail);
        _mail = view.findViewById(R.id.mail_Detail);
        _address = view.findViewById(R.id.postal_Detail);

        return view;
    }

    public void displayDetails(Contacts contact) {

        String avatar;
        int imageResource;

        avatar = contact.getAvatar();
        imageResource = getResources().getIdentifier(avatar, null, "com.example.ft_hangout");
        Drawable res = ContextCompat.getDrawable(getContext(), imageResource);

        _avatar.setImageDrawable(res);
        _fullname.setText(contact.getLastname() + " " + contact.getFirstname());
        _lastname.setText(contact.getLastname());
        _firstname.setText(contact.getFirstname());
        _numobile.setText(String.valueOf(contact.getNumobile()));
        _nufix.setText(String.valueOf(contact.getNufix()));
        _mail.setText(contact.getMail());
        _address.setText(contact.getAddress());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getLifecycle();
        contactsViewModel = ViewModelProviders.of(getActivity()).get(ContactsViewModel.class);
        contact = contactsViewModel.getSelectedContact().getValue();
        displayDetails(contact);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_details, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update:
                fragmentTransaction = getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContatUpdateFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;

            case R.id.action_delete:
                contactsViewModel.delete(contact);
                Toast.makeText(this.getContext(), "Contact Supprimé !!!!", Toast.LENGTH_LONG).show();
                getActivity().onBackPressed();
                return true;

            case R.id.action_exit_details:
                getActivity().finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}


/*contactsViewModel.getSelectedContact().observe(getViewLifecycleOwner(), new Observer<Contacts>() {
@Override
public void onChanged(@Nullable Contacts contacts) {

        }
        });*/


       /* contactsViewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(@Nullable List<Contacts> Contacts) {
                ContactDetailsFragment.this.displayDetails(contactsViewModel.getContactDetails(contact));*/

/*-----------------------------------------------------------------------------------------------------*/
//{

// contactsViewModel.getAllContacts().observe(getViewLifecycleOwner(), new Observer<List<Contacts>>() {
        /*    @Override
            public void onChanged(@Nullable List<Contacts> contacts) {


            }

        });*/
//         }
//      }
//   });



