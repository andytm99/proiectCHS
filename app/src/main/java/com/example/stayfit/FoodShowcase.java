package com.example.stayfit;

public class FoodShowcase {
    private String name,brandName,barcode;
    public FoodShowcase()
    {

    }

    public FoodShowcase(String name, String brandName, String barcode) {
        this.name = name;
        this.brandName = brandName;
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
