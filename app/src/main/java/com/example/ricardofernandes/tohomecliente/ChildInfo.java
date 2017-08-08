package com.example.ricardofernandes.tohomecliente;

/**
 * Created by RicardoFernandes on 07/06/2017.
 */

public class ChildInfo {

    private String sequence = "";
    private String name = "";
    private boolean checked = false;


    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public boolean getProductStatus(){
        return true;
    }

    public void setStatus(boolean status) {this.checked = status;}

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}


