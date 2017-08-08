package com.example.ricardofernandes.tohomecliente;

/**
 * Created by RicardoFernandes on 07/06/2017.
 */

public class Projeto {
    private String name;
    private String address;
    private boolean gender;
    public Projeto(String name,boolean gender,String address){
        this.name=name;
        this.gender=gender;
        this.address=address;
    }
    public String getName(){
        return  name;
    }
    public Boolean isGender(){
        return gender;
    }
    public String getAddress(){
        return address;
    }

}