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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(String targetWeight) {
        this.targetWeight = targetWeight;
    }
}
