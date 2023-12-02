package com.example.passtools;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;
import com.example.passtools.model.PassTest;

/**
 * TestActivity, openned in MainActivity allows user to test strength of entered passwords
 *
 * @author hunter biddington
 */

public class TestActivity extends AppCompatActivity {

    /**
     * Views for TestActivity, used for user input and response from model class calls
     */
    Switch hideText;
    Button test_Button_REAL, back_button;
    PassTest temp;
    EditText userInput;

    String userPassword;

    /**
     * creates instance of TestActivity and its subsequent view(s)
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        userInput = (EditText) findViewById(R.id.userInput);

        hideText = (Switch) findViewById(R.id.hideToggle);

        test_Button_REAL = (Button) findViewById(R.id.test_button);

        back_button = (Button)  findViewById(R.id.return_button);

        test_Button_REAL.setOnClickListener(new View.OnClickListener() {

            /**
             * OnClickListener for testButton calls TestPass
             */
            @Override
            public void onClick(View v) {
             // hideText.setText(userInput.getText());
                String passwrdRating = testPass(userInput.getText().toString());
                Toast.makeText(v.getContext(),passwrdRating,Toast.LENGTH_SHORT).show();
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            /**
             * OnClickListener for back button opens previous activity
             */
            @Override
            public void onClick(View v) {
                openMainActivity();

            }
        });
    }

    /**
     * OnSwitchClick for hideText switch, checks state of switch and hides/unhides password
     * @param view
     */
    public void onSwitchClick(View view){
        if (hideText.isChecked()){
            userInput.setInputType(InputType.TYPE_CLASS_TEXT);
           hideText.setText("Hide Password");
        }
        else {
            userInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            hideText.setText("View Password");

        }

    }

    /**
     * testPass will call model class to check strength of password based on an algorithm
     * @param userPassword
     * @return String defining strength of password
     */
    public String testPass(String userPassword){
        temp = new PassTest("abcdefghijklmonpqrstuvwxyz","ABCDEFGHIJKLMOPQRSTUVWXYZ","1234567890","!@#$%^&*");
       //temp = new PassTest("NULL","NULL","NULL","NULL");
        String passRating = temp.passwordExam(userPassword,this);

        return passRating;

    }

    /**
     * Opens MainActivity
     * @return void
     */
    void openMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}