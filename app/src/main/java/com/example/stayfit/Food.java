package com.example.stayfit;

public class Food {
    private String foodName,brandName,calories,carbs,fats,proteins,barcode;

    public Food()
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
}
