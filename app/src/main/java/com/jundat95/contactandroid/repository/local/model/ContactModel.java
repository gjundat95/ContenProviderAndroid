package com.jundat95.contactandroid.repository.local.model;

import android.graphics.Bitmap;

/**
 * Created by tinhngo on 26/09/2017.
 */

public class ContactModel {

    private String id;
    private String name;
    private String phoneNumber;
    private Bitmap avatar;

    public ContactModel(String id, String name, String phoneNumber, Bitmap avatar) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
    }

    public ContactModel(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }
}
