package com.example.passtools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Main Activity, servers as homepage for user for navigation of other views
 *
 * @author hunter biddington
 */

public class MainActivity extends AppCompatActivity {

    /**
     * Button views for navigation to other Activities
     */
    private Button openTest, openGen,openStore;

    /**
     * Creates instance of MainActivity and its subsequent view(s)
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openTest = (Button) findViewById(R.id.test_button);
        openGen =(Button) findViewById(R.id.make_button);
        openStore = (Button) findViewById(R.id.store_button);
        /**
         * OnClickListener for openGen opens GenActivity
         */
        openGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGenActivity();
            }
        });
        /**
         * OnClickListener for openTest opens TestActivity
         */
        openTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTestActivity();
            }
        });
        /**
         * OnClickListener for openStore opens RecallActivity
         */
        openStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStoreActivity();
            }
        });

    }

    /**
     * Opens GenActivity
     * @return void
     */
    void openGenActivity(){
        Intent intent = new Intent(this,GenActivity.class);
        startActivity(intent);
    }
    /**
     * Opens TestActivity
     * @return void
     */

    void openTestActivity(){
        Intent intent = new Intent(this,TestActivity.class);
        startActivity(intent);
    }
    /**
     * Opens StoreActivity
     * @return void
     */
    void openStoreActivity(){
        Intent intent = new Intent(this,RecallActivity.class);
        startActivity(intent);
    }
}