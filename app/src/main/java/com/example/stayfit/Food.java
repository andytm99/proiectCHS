package com.example.stayfit;

public class Food {
    private String foodName,brandName,calories,carbs,fats,proteins,barcode;

    public Food(String name, String calories)
    {

    }
    public Food(String foodName,String brandName,String calories,String carbs,String fats, String proteins,String barcode)
    {
        this.foodName=foodName;
        this.brandName=brandName;
        this.calories=calories;
        this.carbs=carbs;
        this.fats=fats;
        this.proteins=proteins;
        this.barcode=barcode;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
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
