package com.example.stayfit;

public class FoodDiary {
    private String email,day,month,year,cantitate;
    private String foodName,category,brandName,calories,carbs,fats,proteins,barcode;

    public FoodDiary(){

    }
    public FoodDiary(String email,String day, String month,String year,String cantitate,String foodName,String category,String brandName,String calories,String carbs,String fats, String proteins,String barcode){
        this.email=email;
        this.day=day;
        this.month=month;
        this.year=year;
        this.cantitate=cantitate;
        this.foodName=foodName;
        this.category=category;
        this.brandName=brandName;
        this.calories=calories;
        this.carbs=carbs;
        this.fats=fats;
        this.proteins=proteins;
        this.barcode=barcode;
    }

    public String getCantitate() {
        return cantitate;
    }

    public void setCantitate(String cantitate) {
        this.cantitate = cantitate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public String getProteins() {
        return proteins;
    }

    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
