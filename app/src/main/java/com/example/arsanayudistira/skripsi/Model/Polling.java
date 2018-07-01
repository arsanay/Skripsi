package com.example.arsanayudistira.skripsi.Model;

/**
 * Created by Arsana Yudistira on 12/22/2017.
 */

public class Polling {
    private String plain;
    private String enkrip;
    private String dekrip;
   private String sha3_224;


    public Polling(){

    }
    public Polling(String plain,String enkrip,String dekrip,String sha3_224){
        this.plain = plain;
        this.enkrip = enkrip;
        this.dekrip = dekrip;
        this.sha3_224 = sha3_224;

    }

    public String getPlain() {
        return plain;
    }

    public void setPlain(String plain) {
        this.plain = plain;
    }

   public String getEnkrip() {
        return enkrip;
   }

    public void setEnkrip(String enkrip) {
        this.enkrip = enkrip;
    }

    public String getDekrip() {
        return dekrip;
   }

   public void setDekrip(String dekrip) {
        this.dekrip = dekrip;
    }

    public String getSha3_224() {
        return sha3_224;
    }

    public void setSha3_224(String sha3_224) {
        this.sha3_224 = sha3_224;
    }


}
