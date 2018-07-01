package com.example.arsanayudistira.skripsi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

/**
 * Created by Arsana Yudistira on 12/6/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_login;
    EditText ET_NIK;
    EditText ET_Pass;
    ProgressDialog progressDialog;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("user");

    public LoginActivity() {
    }

    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.login_acivity);

        ET_NIK = (EditText)findViewById(R.id.ET_NIK);
        ET_Pass = (EditText)findViewById(R.id.password);
        btn_login = (Button)findViewById(R.id.btn_login);
        progressDialog = new ProgressDialog(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((ET_NIK.getText().toString().trim().length()!=16)){
                    Snackbar.make(view,"Mohon Cek Kembali Username & Password Anda!", Snackbar.LENGTH_SHORT).setAction("Action",null).show();
                } else  if((ET_Pass.getText().toString().trim().length()<6)){
                    Snackbar.make(view,"Mohon Cek Kembali Username & Password Anda!", Snackbar.LENGTH_SHORT).setAction("Action",null).show();}
                    else {
            userLogin(); }
            }
        });
    }

    private void userLogin(){
       final String nik = ET_NIK.getText().toString().trim();
       final String pass = ET_Pass.getText().toString().trim();
        String log = nik+"_"+pass;

        if(TextUtils.isEmpty(nik)){
            Toast.makeText(this,"Harap Masukan NIK Anda",Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Harap Masukan Password Anda",Toast.LENGTH_SHORT).show();
        } else if (!TextUtils.isEmpty(nik) && (!TextUtils.isEmpty(pass))){

          final String newkey = potongdata(nik,pass);
            System.out.println(newkey);
            mDatabase.orderByChild("log").equalTo(log)  .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("tes debug",String.valueOf(dataSnapshot.child("user").child("nik").getValue()));

                    if(dataSnapshot.exists() ){
                        Toast.makeText(LoginActivity.this,"Berhasil",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,polingActivity.class);
                        intent.putExtra("keybaru",newkey);
                        startActivity(intent);
                        finish();
                       // startActivity(new Intent(LoginActivity.this,polingActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this,"Gagal",Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }




                });







        }
    }

    public  String potongdata(String nik, String pass){
        String newpass = new String();
        String newnik = new String();
        String newkey ;
        for (int i = 0;i<6;i++) {

            String temp = Character.toString(pass.charAt(i));
            newpass += temp;
        }
        for (int i =4;i>0;i--){
            String temp =Character.toString(nik.charAt(16-i));

            newnik += temp;
        }
        newkey = newpass+newnik;
        return newkey;
    }


    @Override
    public void onClick(View view) {

    }
}
