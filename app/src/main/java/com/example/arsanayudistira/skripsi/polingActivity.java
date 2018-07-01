package com.example.arsanayudistira.skripsi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.arsanayudistira.skripsi.Controller.Keccak;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.arsanayudistira.skripsi.Controller.Grain;
import static com.example.arsanayudistira.skripsi.Utils.Parameters.KECCAK_224;
import static com.example.arsanayudistira.skripsi.Utils.Parameters.KECCAK_256;
import static com.example.arsanayudistira.skripsi.Utils.Parameters.KECCAK_384;
import static com.example.arsanayudistira.skripsi.Utils.Parameters.KECCAK_512;
import static com.example.arsanayudistira.skripsi.Utils.Parameters.SHA3_224;
import static com.example.arsanayudistira.skripsi.Utils.Parameters.SHA3_256;
import static com.example.arsanayudistira.skripsi.Utils.Parameters.SHA3_384;
import static com.example.arsanayudistira.skripsi.Utils.Parameters.SHA3_512;
import static com.example.arsanayudistira.skripsi.Utils.Parameters.SHAKE128;
import static com.example.arsanayudistira.skripsi.Utils.Parameters.SHAKE256;
import static com.example.arsanayudistira.skripsi.Utils.HexUtils.getHex;
import com.example.arsanayudistira.skripsi.Model.Polling;


/**
 * Created by Arsana Yudistira on 12/20/2017.
 */

public class polingActivity extends AppCompatActivity{

    Button bt_pilih;
    RadioButton rb_paslon1;
    RadioButton rb_paslon2;
    public polingActivity(){

    }
    DatabaseReference databaseUser;
    public void onCreate(Bundle savedInstance) {

        super.onCreate(savedInstance);
        setContentView(R.layout.poling);
        Intent intent = this.getIntent();
        final String newkey = intent.getStringExtra("keybaru");

        //String id = databaseUser.push().getKey();
        databaseUser = FirebaseDatabase.getInstance().getReference("Polling");
        final Grain grain = new Grain();
        final Keccak keccak = new Keccak();
        bt_pilih = (Button)findViewById(R.id.buttonpilih);
        rb_paslon1 = (RadioButton)findViewById(R.id.rb_pasangansatu);
        rb_paslon2 = (RadioButton)findViewById(R.id.rb_pasangandua);

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        bt_pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long start;
                long end;
                start = System.nanoTime();//menghitung proses dalam detik
                System.out.println("ini adalah newkey "+newkey);
                if (rb_paslon1.isChecked()) {

                    grain.init("sekripsi",newkey);
                    grain.keystream("paslon1");
                    grain.inisialisasikunci();
                    String encrypt =grain.hasilE();
                    String decrypt = grain.hasilD();
                    System.out.println(encrypt);
                  //  String s = stringToBinary("paslon1",SHA3_224);
                    String sha3_224 = keccak.getHash(encrypt, SHA3_224);


                  String id = databaseUser.push().getKey();

                   Polling polling = new Polling("paslon1",encrypt,decrypt,sha3_224);
                    databaseUser.child(id).setValue(polling);
                    Toast.makeText(polingActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(polingActivity.this,AkhirActivity.class);

                    startActivity(intent);
                    finish();


                } else if (rb_paslon2.isChecked()) {
                    int temp = rb_paslon2.getId();
                    String hasil = Integer.toString(temp);
                    grain.init("sekripsi",newkey);
                    grain.inisialisasikunci();
                    grain.keystream("paslon2");
                    String encrypt =grain.hasilE();
                    String decrypt = grain.hasilD();
                   System.out.println(encrypt);
                   // String s = stringToBinary("paslon2");
                    String sha3_224 = keccak.getHash(encrypt, SHA3_224);
                  //  System.out.println("sha3-224 = " + sha3_224);
                    String id = databaseUser.push().getKey();
                   Polling polling = new Polling("paslon2",encrypt,decrypt,sha3_224);
                   databaseUser.child(id).setValue(polling);
                    Toast.makeText(polingActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(polingActivity.this,AkhirActivity.class));
                    finish();
                }
                end = System.nanoTime();
                System.out.println("\nwaktu yang diperlukan selama proses : " + (end - start)+ " nano detik");
            }
        });
    }
    public static String stringToBinary(String string){
        String result = "";
        String tmpStr;
        int tmpInt;
        char[] messChar = string.toCharArray();
        for (int i = 0; i < messChar.length; i++) {
            tmpStr = Integer.toBinaryString(messChar[i]);

            tmpInt = tmpStr.length();

            if (tmpInt !=8){
                tmpInt = 8-tmpInt;
                if (tmpInt ==8){
                    result += tmpStr;
                } else if (tmpInt > 0 ){
                    for (int j = 0; j < tmpInt; j++) {
                        result += "0";

                    }
                } else {
                    System.out.println("arguments bits to small ...");
                }
            }
            result += tmpStr;
            //
        }
        result += "";


        return result;

    }


}
