package com.example.stayfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFoodActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonAdd;
    private EditText productName,brandName,calories,carbs,proteins,fats,barcode;
    private ImageView backButton,barcodeScannerButton;
    private DatabaseReference databaseReference;

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

        databaseReference= FirebaseDatabase.getInstance("https://food-calorie-counter-a107a-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Foods");

        productName=(EditText)findViewById(R.id.editTextProductName);
        brandName=(EditText)findViewById(R.id.editTextBrandName);
        calories=(EditText)findViewById(R.id.editTextCalories);
        carbs=(EditText)findViewById(R.id.editTextCarbs);
        proteins=(EditText)findViewById(R.id.editTextProteins);
        fats=(EditText)findViewById(R.id.editTextFat);
        barcode=(EditText)findViewById(R.id.editTextBarcode);
        String codBareSmech;
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            codBareSmech = bundle.getString("Camera");
            if (codBareSmech != null) {
                barcode.setText(codBareSmech);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonAddFoodToDatabase:
                addFoodToDatabase();
//                startActivity(new Intent(this,FoodActivity.class ));
                break;
            case R.id.backButtonAddToDatabase:
                startActivity(new Intent(this,FoodActivity.class ));//FoodActivity este food menu
                break;
            case R.id.scanBarcodeButton:
                startActivity(new Intent(this,CodeScannerActivity.class));
                break;

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

        if(codBare.isEmpty())
        {
            barcode.setError("Barcode is required!");
            barcode.requestFocus();
            return;
        }
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
            return;
        }
        if(carbo.isEmpty())
        {
            carbs.setError("Carbs are required!");
            carbs.requestFocus();
            return;
        }
        if(proteine.isEmpty())
        {
            proteins.setError("Proteins are required!");
            proteins.requestFocus();
            return;
        }
        if(grasimi.isEmpty())
        {
            fats.setError("Fats are required!");
            fats.requestFocus();
            return;
        }


        Food foods=new Food(product,brand,calorii,carbo,grasimi,proteine,codBare);
        databaseReference.push().setValue(foods).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(AddFoodActivity.this,"Food inserted successfully into the database!",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),MainMenuActivity.class));
                }
                else{
                    Toast.makeText(AddFoodActivity.this,"Food failed insertion into the database!",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
