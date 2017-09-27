package com.jundat95.contactandroid.ui.contact;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.jundat95.contactandroid.R;
import com.jundat95.contactandroid.repository.local.model.ContactModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ContentResolver contentResolver;
    private Uri uriContact = ContactsContract.Contacts.CONTENT_URI;
    private Uri uriContactHashPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private List<ContactModel> contactModels = new ArrayList<>();

    private String Contact_Id = ContactsContract.Contacts._ID;
    private String Contact_Display_Name = ContactsContract.Contacts.DISPLAY_NAME;
    private String Has_Phone_Number = ContactsContract.Contacts.HAS_PHONE_NUMBER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        init();

    }

    private void init() {
        contentResolver = this.getContentResolver();
        recyclerView = (RecyclerView) findViewById(R.id.rcvContact);
        getAllContact();
        ContactAdapter contactAdapter = new ContactAdapter(this,contactModels);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getAllContact() {
        Cursor cursor = contentResolver.query(
                uriContact,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {

            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

//            Toast.makeText(
//                    this,
//                    "1-"+     cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))+
//                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
//                    ,
//                    Toast.LENGTH_SHORT).show();

            if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {


                String select = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";
                Cursor cursorHashPhone = contentResolver.query(
                        uriContactHashPhone,
                        null,
                        select,
                        new String[] {id},
                        null

                );

                while (cursorHashPhone.moveToNext()) {
//                    Toast.makeText(
//                            this,
//                            "2-"+     cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))+
//                                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))+
//                                    cursorHashPhone.getString(cursorHashPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
//                            ,
//                            Toast.LENGTH_SHORT).show();

                    ContactModel contactModel = new ContactModel(
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)),
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)),
                            cursorHashPhone.getString(cursorHashPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                            retrieveContactPhoto(id)
                    );

                    contactModels.add(contactModel);
                }
            }
        }



    }

    private Bitmap retrieveContactPhoto(String contactID) {

        Bitmap photo = null;

        InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(),
                ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactID)));

        if (inputStream != null) {
            return BitmapFactory.decodeStream(inputStream);

        }

        return photo;
    }

}
