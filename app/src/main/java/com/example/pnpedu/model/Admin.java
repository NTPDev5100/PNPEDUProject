package com.example.pnpedu.model;

import android.text.TextUtils;

public class Admin {

    private int id;
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int isValidl()
    {
        if(TextUtils.isEmpty(getUsername()))
        {
            return 0;
        }
        else if(TextUtils.isEmpty(getPassword())){
            return 1;
        }
        else if (getPassword().length() <6){
            return 2;
        }
        else
            return -1;

    }

}
