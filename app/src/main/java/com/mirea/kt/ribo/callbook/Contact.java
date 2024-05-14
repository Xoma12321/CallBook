package com.mirea.kt.ribo.callbook;

import io.realm.RealmObject;

public class Contact extends RealmObject {
    private String contactName;
    private String contactPhoneNumber;
    private String contactAvatar;

    public Contact(String contactName, String contactPhoneNumber, String contactAvatar) {
        this.contactName = contactName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.contactAvatar = contactAvatar;
    }

    public Contact() {
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getContactAvatar() {
        return contactAvatar;
    }

    public void setContactAvatar(String contactAvatar) {
        this.contactAvatar = contactAvatar;
    }
}
