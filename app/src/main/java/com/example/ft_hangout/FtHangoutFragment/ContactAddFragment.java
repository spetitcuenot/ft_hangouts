package com.example.ft_hangout.FtHangoutFragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.example.ft_hangout.CircularImageView;
import com.example.ft_hangout.Entity.Contacts;
import com.example.ft_hangout.R;
import com.example.ft_hangout.ViewModel.ContactsViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;



public class ContactAddFragment extends Fragment implements View.OnClickListener {


    private ContactsViewModel contactsViewModel;
    private Contacts contact;
    private ImageView _avatar;
    private EditText _lastname;
    private EditText _firstname;
    private EditText _numobile;
    private EditText _nufix;
    private EditText _mail;
    private EditText _address;
    private TextView _fullname;
    private final int PICK_IMAGE_REQUEST_CODE = 1000;
    private final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 1001;
    private static final String IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg";//temp file
    private Bitmap _bitmap;
    private File _file;
    private Uri _uriPath;
    private ProgressDialog _dialog = null;
    private String _imagepath = null;
    private String _upLoadServerUri;


    private FloatingActionButton _closeButton;


    public ContactAddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_add_fragment, container, false);

        _avatar = view.findViewById(R.id.image_Button_add);
        _fullname = view.findViewById(R.id.create_contact);
        _lastname = view.findViewById(R.id.lastname_add);
        _firstname = view.findViewById(R.id.firstmame_add);
        _numobile = view.findViewById(R.id.mobile_add);
        PhoneNumberUtils.formatNumber(_numobile.getText().toString());
        _numobile.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        _nufix = view.findViewById(R.id.fixe_add);
        _nufix.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        PhoneNumberUtils.formatNumber(_nufix.getText().toString());
        _mail = view.findViewById(R.id.mail_add);
        _address = view.findViewById(R.id.postal_add);
        ImageButton imgButtonSave = view.findViewById(R.id.save);
        ImageButton imageButtonCancel = view.findViewById(R.id.cancel);
        CircularImageView imageButtonAdd = view.findViewById(R.id.image_Button_add);

        imgButtonSave.setOnClickListener(this);
        imageButtonCancel.setOnClickListener(this);
        imageButtonAdd.setOnClickListener(this);


        return view;
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

    }


    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.save:
                saveContact();
                if (contact.getLastname() != null && contact.getNumobile() != null) {
                    contactsViewModel.insert(contact);
                    //  contactsViewModel.update(contact);
                    Toast.makeText(this.getContext(), "New contact saved !", Toast.LENGTH_LONG).show();
                    //  getParentFragment().getFragmentManager().getPrimaryNavigationFragment().onAttach(getContext());
                     getActivity().onBackPressed();
                } else {
                    Toast.makeText(this.getContext(), "Can't save please update lastname and mobile number !", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.cancel:
                getActivity().onBackPressed();
                break;

            case R.id.image_Button_add:
                pickImage();
              /*  Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 1);*/

             /* Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                startActivityForResult(chooserIntent, PICK_IMAGE_REQUEST);*/


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        _uriPath = data.getData();
        Bundle extras = data.getExtras();
       // _imagepath = data.getData().getPath();
        _bitmap = (Bitmap) extras.get("data");


       // _avatar.setImageBitmap(_bitmap);
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (extras == null) {
              Toast.makeText(this.getContext(), "There no data to save !", Toast.LENGTH_LONG).show();
            } else {
               // getUriPath(_bitmap);

               // Uri.fromFile(IMAGE_PATH);
               Toast.makeText(this.getContext(), "DATA can be saved !!!!!!!!!! " + _imagepath, Toast.LENGTH_LONG).show();
                _avatar.setImageBitmap(_bitmap);
                //  _imagepath = getPath(_filePath);
              /* if (_uriPath != null) {
                    if (uriToFile(_uriPath) != null) {
                        Toast.makeText(this.getContext(), "File !", Toast.LENGTH_LONG).show();
                        _file = uriToFile(_uriPath);

                    } else if (uriToBitmap(_uriPath) != null) {
                        _bitmap = uriToBitmap(_uriPath);
                        Toast.makeText(this.getContext(), "Bitmap !", Toast.LENGTH_LONG).show();
                        _avatar.setImageBitmap(_bitmap);
                    }
                }*/


            }
        }


    }

    private void pickImage() {
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
           openPickImage();
        } else {
           requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE_REQUEST_CODE);

        }
    }

  @NonNull
  @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // pick image after request permission success
                openPickImage();
                Toast.makeText(this.getContext(), "PERMISSION GRANTED !", Toast.LENGTH_LONG).show();

            }
        }
    }

    public void openPickImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(_uriPath,"image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("aspectX", 16);
        intent.putExtra("aspectY", 9);
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
    }

    public void getUriPath(Bitmap bitmap)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        _imagepath = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), bitmap, "Title", null);


    }
    public File uriToFile(Uri uri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumn, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndex(filePathColumn[0]);
                File picturePath = new File(cursor.getString(column_index));
                // int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                // return cursor.getString(column_index);
                cursor.close();
                return picturePath;
            }
            cursor.close();
        }
        return null;
    }




    private Bitmap uriToBitmap(Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

