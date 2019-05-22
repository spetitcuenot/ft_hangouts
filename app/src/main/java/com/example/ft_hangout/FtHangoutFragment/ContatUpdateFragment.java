package com.example.ft_hangout.FtHangoutFragment;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ft_hangout.CircularImageView;
import com.example.ft_hangout.Entity.Contacts;
import com.example.ft_hangout.R;
import com.example.ft_hangout.ViewModel.ContactsViewModel;

public class ContatUpdateFragment extends Fragment implements View.OnClickListener{

    private ContactsViewModel contactsViewModel;
    private static Contacts contact;
    private CircularImageView _avatar;
    private TextView _lastname;
    private TextView _firstname;
    private TextView _numobile;
    private TextView _nufix;
    private TextView _mail;
    private TextView _address;
    private TextView _fullname;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_add_fragment, container, false);

        _avatar = view.findViewById(R.id.image_Button_add);
        _fullname = view.findViewById(R.id.create_contact);
        _lastname = view.findViewById(R.id.lastname_add);
        _firstname = view.findViewById(R.id.firstmame_add);
        _numobile = view.findViewById(R.id.mobile_add);
        _nufix = view.findViewById(R.id.fixe_add);
        _mail = view.findViewById(R.id.mail_add);
        _address = view.findViewById(R.id.postal_add);
        ImageButton imgButtonSave = view.findViewById(R.id.save);

        imgButtonSave.setOnClickListener(this);

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

    public void saveContact() {
        //  String avatar = _avatar.getDrawable().toString();
        String avatar = "@drawable/avatar101";
        String lastname = _lastname.getText().toString();
        String firstname = _firstname.getText().toString();
        String numobile = _numobile.getText().toString();
        String nufix = _nufix.getText().toString();
        String mail = _mail.getText().toString();
        String address = _address.getText().toString();
        contact = new Contacts(avatar, lastname, firstname, numobile, nufix, mail, address);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                saveContact();
                if (contact.getLastname() != null && contact.getNumobile() != null) {
                    contactsViewModel.update(contact);
                    contactsViewModel.getSelectedContact().setValue(contact);
                    getActivity().onBackPressed();
                }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
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
