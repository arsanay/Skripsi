package com.example.arsanayudistira.skripsi.Controller;

import android.content.Context;

import com.example.arsanayudistira.skripsi.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Arsana Yudistira on 12/22/2017.
 */

public class UserController {
    private Context context;
    private User user;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("user");

    public UserController(){

    }

    public UserController(Context context){
        this.context = context;
       // mDatabase = new DatabaseReference(this.context,null);
    }

}
