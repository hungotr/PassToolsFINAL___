package com.example.passtools.model;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.passtools.EncodeDecode;
import com.example.passtools.Password;

/**
 * Account class represents a single account object that will store a website, username, and an encrypted password
 *
 * @author Hunter Biddington
 */
public class Account  {

    /**
     * local variables for account
     */
    public String website, userName, userPass;

    /**
     * encrypted password object
     */
    public Password kramer = new Password("fortnite123");

    /**
     * Constructor for Password Object
     * @param websiteB
     * @param usernameB
     * @param userPassB
     */
    public Account(String websiteB, String usernameB, String userPassB) {
        this.website = websiteB;
        this.userName = usernameB;
        this.userPass = userPassB;
    }

    /**
     * Getters and Setters for Account class
     * @return String representation of password/usename/website
     */
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    /**
     * ToString method for Account Object
     * @return String representation of Account Object
     */
    @Override
    public String toString() {
        return "Account{" +
                "website='" + website + '\'' +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                "}\n\n";
    }


    }


