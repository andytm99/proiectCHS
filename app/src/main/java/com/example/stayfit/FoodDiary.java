package com.example.stayfit;

public class FoodDiary {
    private String email,day,month,year;
    private String foodName,category,brandName,calories,carbs,fats,proteins,barcode;

    public FoodDiary(){

    }
    public FoodDiary(String email,String day, String month,String year,String foodName,String category,String brandName,String calories,String carbs,String fats, String proteins,String barcode){
        this.email=email;
        this.day=day;
        this.month=month;
        this.year=year;
        this.foodName=foodName;
        this.category=category;
        this.brandName=brandName;
        this.calories=calories;
        this.carbs=carbs;
        this.fats=fats;
        this.proteins=proteins;
        this.barcode=barcode;
    }

}
