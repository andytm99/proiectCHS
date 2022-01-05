package com.example.stayfit;

public class User {
    public String email,password,height,weight,day,month,year,targetWeight;

    public User(){

    }
    public User(String email, String password,String day, String month,String year,String height, String weight,String targetWeight){
        this.email=email;
        this.password=password;
        this.day=day;
        this.month=month;
        this.year=year;
        this.height=height;
        this.weight=weight;
        this.targetWeight=targetWeight;
    }
}
