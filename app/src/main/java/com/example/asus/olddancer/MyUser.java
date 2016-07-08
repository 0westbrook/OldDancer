package com.example.asus.olddancer;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser{
    private String username;
    private String password;

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public String getUsername(){
        return username;
    }
}
