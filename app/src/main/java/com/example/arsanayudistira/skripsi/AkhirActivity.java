package com.example.arsanayudistira.skripsi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Arsana Yudistira on 1/1/2018.
 */

public class AkhirActivity extends AppCompatActivity {
    Button bt_selesai;

    public AkhirActivity(){

    }
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.tampilanakhir);
        bt_selesai = (Button)findViewById(R.id.btn_logout);

        bt_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();

            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Closed", Toast.LENGTH_LONG).show();
    }
}