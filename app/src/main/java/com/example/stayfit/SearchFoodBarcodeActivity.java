package com.example.stayfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.TimeZone;

public class SearchFoodBarcodeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backButton,scanner;
    private EditText barcode,quantity,category;
    private Button addButton;
    protected Food food;
    FirebaseAuth auth;

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_food_name_barcode_view);

        backButton=(ImageView) findViewById(R.id.backButtonSearchBarcode);
        backButton.setOnClickListener(this);

        addButton=(Button)findViewById(R.id.buttonAddFoodDiaryToDatabaseBarcode);
        addButton.setOnClickListener(this);

        scanner=(ImageView)findViewById(R.id.scanBarcodeButtonSearch) ;
        scanner.setOnClickListener(this);

        barcode =(EditText)findViewById(R.id.editTextBarcodeBarcode);
        quantity=(EditText)findViewById(R.id.editTextQuantityBarcode) ;
        category=(EditText)findViewById(R.id.editTextCategoryBarcode) ;

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
            case R.id.backButtonSearchBarcode:
                startActivity(new Intent(this,SearchFoodActivity.class));
                break;
            case R.id.scanBarcodeButtonSearch:
                startActivity(new Intent(this,CodeScannerDiaryActivity.class));
                break;
            case R.id.buttonAddFoodDiaryToDatabaseBarcode:
                addFoodToDiaryDatabase();
                break;

        }
    }

    private void addFoodToDiaryDatabase() {
        DatabaseReference databaseReference,databaseReferenceDiary;
        FirebaseDatabase database;
        String codBare=barcode.getText().toString().trim();
        String cantitate=quantity.getText().toString().trim();
        String categorie=category.getText().toString().trim();
        if(codBare.isEmpty())
        {
            barcode.setError("Barcode is required!");
            barcode.requestFocus();
            return;
        }
        if(cantitate.isEmpty())
        {
            quantity.setError("Quantity in grams is required!");
            quantity.requestFocus();
            return;
        }

        if(categorie.isEmpty())
        {
            category.setError("Category is required!");
            category.requestFocus();
            return;
        }



            //String email = auth.getCurrentUser().getEmail();

        database=FirebaseDatabase.getInstance("https://food-calorie-counter-a107a-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference=database.getReference().child("Foods");
        databaseReferenceDiary=database.getReference().child("DiaryFoods");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds:snapshot.getChildren()){
                    food=ds.getValue(Food.class);
                    if(food.getBarcode().equals(codBare))
                    {
                        Calendar calendar=Calendar.getInstance(TimeZone.getDefault());
                        int day=calendar.get(Calendar.DATE);
                        int month=calendar.get(Calendar.MONTH)+1;
                        int year=calendar.get(Calendar.YEAR);
                        String strDay=String.valueOf(day);
                        String strMonth= String.valueOf(month);
                        String strYear=String.valueOf(year);
                        String calorii=food.getCalories();
                        String nume=food.getFoodName();
                        String numeBrand=food.getBrandName();
                        float fCalorii=Float.parseFloat(calorii);
                        float fCantitate=Float.parseFloat(cantitate);
                        float coeficient=(fCantitate/100);//calculam coeficientul (cantitate/100)
                        String caloriiJurnal=String.valueOf((coeficient*fCalorii));//cantitate/100*calorii produs pe 100g

                        float fCarbohidrati=Float.parseFloat(food.getCarbs());
                        String carboJurnal= String.valueOf(coeficient*fCarbohidrati);

                        float fGrasimi=Float.parseFloat(food.getFats());
                        String grasimiJurnal=String.valueOf(coeficient*fGrasimi);

                        float fProteine=Float.parseFloat(food.getProteins());
                        String proteineJurnal=String.valueOf(coeficient*fProteine);
                        String codDeBare=food.getBarcode();

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            String userEmail = user.getEmail();
                            FoodDiary foodDiary=new FoodDiary(userEmail,strDay,strMonth,strYear,cantitate,nume,categorie,numeBrand,caloriiJurnal,carboJurnal,grasimiJurnal,proteineJurnal,codDeBare);
                            databaseReferenceDiary.push().setValue(foodDiary).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(SearchFoodBarcodeActivity.this,"Food inserted successfully into the diary database!",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(),DiaryActivity.class));
                                    }
                                    else{
                                        Toast.makeText(SearchFoodBarcodeActivity.this,"Food failed insertion into the diary database!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }



                    }
                    Toast.makeText(SearchFoodBarcodeActivity.this,"No food with that bar code!",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
