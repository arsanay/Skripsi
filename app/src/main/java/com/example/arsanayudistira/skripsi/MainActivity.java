package com.example.arsanayudistira.skripsi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arsanayudistira.skripsi.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
public EditText ET_NIK;
private EditText ET_Pass;
private Button btn_submit;
private TextView tv_login;

DatabaseReference databaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseUser = FirebaseDatabase.getInstance().getReference("user");

        ET_NIK = (EditText)findViewById(R.id.ET_NIK);
        ET_Pass = (EditText)findViewById(R.id.password);
        btn_submit = (Button)findViewById(R.id.btn_daftar);
        tv_login = (TextView)findViewById(R.id.tv_login);

        btn_submit.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }
    private void addUser(){
        String nik = ET_NIK.getText().toString().trim();
        String password = ET_Pass.getText().toString().trim();
        String log = nik+"_"+password;

        if (!TextUtils.isEmpty(nik) && (!TextUtils.isEmpty(password))) {
            String id = databaseUser.push().getKey();
            System.out.println(id);
             User user = new User(nik,password,log);
             databaseUser.child(id).setValue(user);
            Toast.makeText(this, "User Berhasil Dibuat", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class
            ));
            finish();
        } else if (TextUtils.isEmpty(nik)) {
            Toast.makeText(this,"Harap Masukan NIK Anda",Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Harap Masukan Password Anda", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View view) {
        if (view == btn_submit) {
            addUser();
        }
        if (view == tv_login) {
            startActivity(new Intent(this, LoginActivity.class
            ));
        }
    }
}
