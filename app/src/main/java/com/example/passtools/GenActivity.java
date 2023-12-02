package com.example.passtools;

import androidx.appcompat.app.AppCompatActivity;
import com.example.passtools.model.PasswordGenerator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

/**
 * GenActivity, openned in MainActivity allows user to generate unique password(s)
 *
 * @author hunter biddington
 */

public class GenActivity extends AppCompatActivity {

    /**
     * local Button views for generating new password(s) / returning to main page
     */
    private Button passwordA, passwordB, passwordC, return_button;

    /**
     * Initializes GenActivity and subsequent view(s)
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen);
        PasswordGenerator tempGen = new PasswordGenerator(14);
        passwordA = (Button) findViewById(R.id.password1);
        passwordB = (Button) findViewById(R.id.password2);
        passwordC = (Button) findViewById(R.id.password3);

        passwordA.setText(generatePassword(tempGen));
        passwordB.setText(generatePassword(tempGen));
        passwordC.setText(generatePassword(tempGen));

        return_button = (Button) findViewById(R.id.back_button);

                 passwordA.setOnClickListener(new View.OnClickListener() {
                     /**
                      * OnClickListener for passwordA calls model classes to generate new password
                      */
                     @Override
                     public void onClick(View v) {
                         passwordA.setText(generatePassword(tempGen));

                     }
                 });
                 passwordB.setOnClickListener(new View.OnClickListener() {
                     /**
                      * OnClickListener for passwordB calls model classes to generate new password
                      */
                     @Override
                     public void onClick(View v) {
                         passwordB.setText(generatePassword(tempGen));
                     }
                 });
                 passwordC.setOnClickListener(new View.OnClickListener() {
                     /**
                      * OnClickListener for passwordC calls model classes to generate new password
                      */
                     @Override
                     public void onClick(View v) {
                         passwordC.setText(generatePassword(tempGen));
                     }
                 });
                 return_button.setOnClickListener(new View.OnClickListener() {
                     /**
                      * OnClickListener for return button, opens MainActivity
                      */

                     @Override
                     public void onClick(View v) {
                         openMainActivity();
                     }
                 });
    }

    /**
     * generatePassword method creates new password
     * @param tempGenB
     * @return String representing new password
     */
    private String generatePassword(PasswordGenerator tempGenB){
        String generatedPass = tempGenB.generatePassword();
        return generatedPass;
    }

    /**
     * opens MainActivity
     * @return void
     */
    private void openMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}