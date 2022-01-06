package com.example.stayfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AddFoodActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonAdd;
    private EditText productName,brandName,calories,carbs,proteins,fats,barcode;
    private FirebaseAuth mAuth;
    private ImageView backButton,barcodeScannerButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food_to_database);

        buttonAdd=(Button) findViewById(R.id.buttonAddFoodToDatabase);
        buttonAdd.setOnClickListener(this);

        backButton=(ImageView)findViewById(R.id.backButtonAddToDatabase);
        backButton.setOnClickListener(this);

        barcodeScannerButton=(ImageView)findViewById(R.id.scanBarcodeButton);
        barcodeScannerButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        productName=(EditText)findViewById(R.id.editTextProductName);
        brandName=(EditText)findViewById(R.id.editTextBrandName);
        calories=(EditText)findViewById(R.id.editTextCalories);
        carbs=(EditText)findViewById(R.id.editTextCarbs);
        proteins=(EditText)findViewById(R.id.editTextProteins);
        fats=(EditText)findViewById(R.id.editTextFat);
        barcode=(EditText)findViewById(R.id.editTextBarcode);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonAddFoodToDatabase:
                addFoodToDatabase();
                break;
            case R.id.backButtonAddToDatabase:
                startActivity(new Intent(this,MainMenuActivity.class ));// vom modifica sa duca spre activitatea jurnalului
                break;
            case R.id.scanBarcodeButton:
                break;//aici o sa avem metoda ce o sa foloseasca barcode scannerul

        }
    }

    private void addFoodToDatabase() {
        String product=productName.getText().toString().trim();
        String brand=brandName.getText().toString().trim();
        String calorii=calories.getText().toString().trim();
        String carbo=carbs.getText().toString().trim();
        String proteine=proteins.getText().toString().trim();
        String grasimi=fats.getText().toString().trim();
        String codBare=barcode.getText().toString().trim();

        if(product.isEmpty())
        {
            productName.setError("Product name missing!");
            productName.requestFocus();
            return;
        }

        if(brand.isEmpty())
        {
            brandName.setError("Brand name missing!");
            brandName.requestFocus();
            return;
        }
        if(calorii.isEmpty())
        {
            calories.setError("Calories are missing!");
            calories.requestFocus();
        }
        if(carbo.isEmpty())
        {
            carbs.setError("Carbs are required!");
            carbs.requestFocus();
        }
        if(proteine.isEmpty())
        {
            proteins.setError("Proteins are required!");
            proteins.requestFocus();
        }
        if(grasimi.isEmpty())
        {
            fats.setError("Fats are required!");
            fats.requestFocus();
        }
        if(codBare.isEmpty())
        {
            barcode.setError("Barcode is required!");
            barcode.requestFocus();
        }
    }
}
