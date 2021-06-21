package com.example.emedicine;

import android.graphics.Bitmap;

public class Person {

    private String code;
    private String med_name;
    private String ent_name;
    private Bitmap img;


    public Person(String code, String med_name, String ent_name, String img){

    }

    public Person(String code, String med_name, String ent_name, Bitmap img){
        this.code = code;
        this.med_name = med_name;
        this.ent_name = ent_name;
        this.img = img;

    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getMed_name(){
        return med_name;
    }

    public void setMed_name(){this.med_name = med_name;}

    public String getEnt_name() {return ent_name;}

    public void setEnt_name(String ent_name) {this.ent_name = ent_name; }

    public Bitmap getImg() {return img;}

    public void setImg(Bitmap img) {this.img = img;}



}
