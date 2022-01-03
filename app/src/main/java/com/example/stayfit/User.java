package com.example.stayfit;

public class User {
    public String email,password,height,weight,day,year,month;

    public User(){

    }
    public User(String email, String password,String day,String year, String month,String height, String weight){
        this.email=email;
        this.password=password;
        this.day=day;
        this.month=month;
        this.year=year;
        this.height=height;
        this.weight=weight;
    }
}
