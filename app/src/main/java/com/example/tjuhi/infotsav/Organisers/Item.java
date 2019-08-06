package com.example.tjuhi.infotsav.Organisers;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by tjuhi on 6/10/2017.
 */

public class Item {
    private String mName;
    private String mDesignation;
    private String mMail;
    private View.OnClickListener requestBtnClickListener;

    public Item() {
    }
    public Item(String mName,String mDesignation,String mMail){
        this.mName=mName;
        this.mMail=mMail;
        this.mDesignation=mDesignation;
    }

    public String getmMail() {
        return mMail;
    }

    public void setmMail(String mMail) {
        this.mMail = mMail;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDesignation() {
        return mDesignation;
    }

    public void setmDesignation(String mDesignation) {
        this.mDesignation = mDesignation;
    }
    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }
    public static ArrayList<Item> getTestingList() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("ABC","Coordinator","abc@gmail.com"));
        items.add(new Item("XYZ","Event Management","xyz@gmail.com"));
        return items;
    }
}
