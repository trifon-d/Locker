package com.example.locker;

public class Account {

    private String UserName,Password,Platform;

    public Account(String UserName, String Password, String Platform){
        this.UserName = UserName;
        this.Password = Password;
        this.Platform = Platform;
    }

    public String getUserName(){return UserName;}
    public String getPassword(){return Password;}
    public String getPlatform(){return Platform;}

    public void setUserName(String UserName){
        this.UserName = UserName;
    }

    public void setPassword(String Password){
        this.Password = Password;
    }

    public void setPlatform(String Platform){
        this.Platform = Platform;
    }
}
