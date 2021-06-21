package com.example.emedicine;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Person {

    private String code;
    private String med_name;
    private String ent_name;


    public Person(String code, String med_name, String ent_name){
        this.code = code;
        this.med_name = med_name;
        this.ent_name = ent_name;

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

    public void setMed_name(String med_name){this.med_name = med_name;}

    public String getEnt_name() {return ent_name;}

    public void setEnt_name(String ent_name) {this.ent_name = ent_name; }


}
