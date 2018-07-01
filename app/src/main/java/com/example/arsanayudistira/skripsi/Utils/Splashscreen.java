package com.example.arsanayudistira.skripsi.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.arsanayudistira.skripsi.LoginActivity;
import com.example.arsanayudistira.skripsi.MainActivity;
import com.example.arsanayudistira.skripsi.R;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent intent;



                        intent = new Intent(Splashscreen.this, LoginActivity.class);

                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.start();
    }
}
