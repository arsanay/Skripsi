package com.example.arsanayudistira.skripsi.Model;

/**
 * Created by Arsana Yudistira on 12/6/2017.
 */

public class User {
    String nik;
    String pass;
    String log;
    public User(){

    }

    public User(String nik, String pass,String log) {
        this.nik = nik;
        this.pass = pass;
        this.log = log;
    }

    public String getNik() {
        return nik;
    }

    public String getPass() {
        return pass;
    }

    public String getLog(){ return log;}
}
