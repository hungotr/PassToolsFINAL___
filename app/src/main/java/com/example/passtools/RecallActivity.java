package com.example.passtools;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;

import com.example.passtools.model.Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

/**
 * Openned in main activity, recall activity will use model classes to store, search, and encrypt passwords locally on internal storage
 *
 * @author hunter biddington
 */

public class RecallActivity extends AppCompatActivity {

    /**
     * Views for user input and ouput for search results and errors regarding account searches and storage
     */

    Button store_search_Button,backButton;

    private Switch hidePass, toggleStrSrch;

    public EditText website, username, password;

    public TextView output, splash;

    /**
     * Arraylist used to store accounts, and integer for number
     */

    public ArrayList<Account> AccountsList = new ArrayList();
    //private int numAccounts = 0;

    private boolean viewMode = true;  // true for store, false for search :3

    /**
     * onCreate initializes RecallActivity + views and their respective onClick listeners
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * Password / EncodeDecode object for encryption for account(s) passwords encrypts them for internal storage
         */

        Password dummyPSWRD = new Password("fortnite123");
        EncodeDecode scramble = new EncodeDecode(dummyPSWRD.getPlaintext()) {
            @Override
            public String encode() throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
                return null;
            }

            @Override
            public String decode() throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
                return null;
            }
        };

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recall);


        hidePass = (Switch) findViewById(R.id.hidePassREAL);
        toggleStrSrch = (Switch) findViewById(R.id.toggle_store_search);

        store_search_Button = (Button) findViewById(R.id.store_search_button);

        backButton = (Button) findViewById(R.id.back_button);

        website = (EditText) findViewById(R.id.userWebsite);
        username = (EditText) findViewById(R.id.userEmail);
        password = (EditText) findViewById(R.id.userPass);

        output = (TextView) findViewById(R.id.userInfo);
        splash = (TextView) findViewById(R.id.splash_text);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });



        store_search_Button.setOnClickListener(new View.OnClickListener() {
            /**
             * OnClickListener for store/search button stores password + account info or searches for stored account
             */
            @Override
            public void onClick(View v) {


                if (viewMode) {
                    if (website.getText().toString().length() > 0 && username.getText().toString().length() > 0 && password.getText().toString().length() > 0) {

                        try {
                            if (storeUserData(website.getText().toString(), username.getText().toString(), password.getText().toString())) {
                                Toast.makeText(v.getContext(), "Account Stored", Toast.LENGTH_SHORT).show();
                               // numAccounts++;
                            } else {
                                Toast.makeText(v.getContext(), "Account Already Exists!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        Toast.makeText(v.getContext(), "Required fields not filled try again :(", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Account tempAccount2 = searchAccountList(website.getText().toString());
                    if (tempAccount2==null){
                        tempAccount2 = searchAccountList(username.getText().toString());
                    }
                    if (tempAccount2 != null){
                        Toast.makeText(v.getContext(), "Account Found!!!", Toast.LENGTH_SHORT).show();
                        output.setText("website: " + tempAccount2.getWebsite() + "\nusername: " + tempAccount2.getUserName() + "\npassword: " + tempAccount2.getUserPass());
                    }
                    else{
                        Toast.makeText(v.getContext(), "Account Not Found, Try Again :<", Toast.LENGTH_SHORT).show();
                    }


                }
                username.setText(null);
                website.setText(null);
                password.setText(null);
            }
        });



    }

    /**
     * OnSwitchClick will check both the search/store mode switch and the hidepassword switch and then toggle visbility of views/passwords as necessary
     * @param view current view
     */


    public void onSwitchClick(View view){
        if (hidePass.isChecked()){
            password.setInputType(InputType.TYPE_CLASS_TEXT);
            hidePass.setText("Hide Password");
        }
        else {
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            hidePass.setText("View Password");

        }
        if (toggleStrSrch.isChecked()){
            store_search_Button.setText("Search");
            toggleStrSrch.setText("Search Mode");
            splash.setText("Enter account info below to search");
            viewMode = false;
            password.setVisibility(View.GONE);
            hidePass.setVisibility(View.GONE);
        }
        else {
            store_search_Button.setText("Store");
            toggleStrSrch.setText("Store Mode");
            splash.setText("Enter account info below to store");
            viewMode = true;
            password.setVisibility(View.VISIBLE);
            hidePass.setVisibility(View.VISIBLE);
            output.setText(null);
        }

    }

    /**
     * Stores data from editText(s), creates temp account object then appends ArrayList with it, then encrypts and stores the Account data internally
     * @param website
     * @param user
     * @param pass
     * @return boolean representing if account was successfully stored, false if account already exists
     * @throws FileNotFoundException
     */


    public boolean storeUserData(String website, String user, String pass ) throws FileNotFoundException {
        for(Account barf : AccountsList){
            if ((barf.getUserName().contains(user)) && (barf.getWebsite().contains(website))){
                return false;
            }
        }
        Account tempAccount = new Account(website,user,pass);
        AccountsList.add(tempAccount);

        String tempLocalAccountSave = tempAccount.toString();
        try{
            writeToFile(tempLocalAccountSave,this);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return true;

    }

    /**
     * searches ArrayList of accounts for an account that satisfies the search query
     * @param query
     * @return Account representing search result, is null if no account is found
     */


    public Account searchAccountList(String query){
        for(Account temp : AccountsList){
            if(temp.getWebsite().contains(query) || temp.getUserName().contains(query)){
                return temp;
            }
        }
        return null;
    }

    /**
     * Writes to internal storage an encrypted password
     * @param data
     * @param context
     * @throws FileNotFoundException
     */
    public void writeToFile(String data, Context context) throws FileNotFoundException {
       File file_path = getApplicationContext().getFilesDir();
       try{
           FileOutputStream writer = new FileOutputStream(new File(file_path,"tempfile01.txt"));
           writer.write(data.getBytes());
           writer.close();
       }
       catch(FileNotFoundException e){
           e.printStackTrace();
        } catch (IOException e) {
           throw new RuntimeException(e);
       }


    }

    /**
     * Opens main activity
     */
    void openMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    /**
     * Opens search activity
     */


}