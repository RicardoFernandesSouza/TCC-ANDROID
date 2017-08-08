package com.example.ricardofernandes.tohomecliente;

import java.util.ArrayList;

/**
 * Created by RicardoFernandes on 21/05/2017.
 */

public class GroupInfoView {
    private String name;
    private ArrayList<ChildInfoView> list = new ArrayList<ChildInfoView>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChildInfoView> getProductList() {
        return list;
    }



    public void setProductList(ArrayList<ChildInfoView> productList) {
        this.list = productList;
    }
}
