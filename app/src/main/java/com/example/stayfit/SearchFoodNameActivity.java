package com.example.stayfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class SearchFoodNameActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backButton;
    private Button addButton;
    private EditText foodName,quantity;
    private Spinner category;
    protected Food food;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_food_name_view);

        backButton=(ImageView) findViewById(R.id.backButtonSearchName);
        backButton.setOnClickListener(this);

        addButton=(Button)findViewById(R.id.buttonAddFoodDiaryToDatabaseName);
        addButton.setOnClickListener(this);

        foodName =(EditText)findViewById(R.id.editTextProductNameSearch);
        quantity=(EditText)findViewById(R.id.editTextQuantityName) ;
        category=(Spinner)findViewById(R.id.spinnerFoodCategoriesName) ;

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.backButtonSearchName:
                startActivity(new Intent(this,SearchFoodActivity.class));
                break;
            case R.id.buttonAddFoodDiaryToDatabaseName:
                addFoodToDiaryDatabaseName();
                break;

        }

    }

    private void addFoodToDiaryDatabaseName() {
        DatabaseReference databaseReference,databaseReferenceDiary;
        FirebaseDatabase database;

        String numeMancare=foodName.getText().toString().trim();
        String cantitate=quantity.getText().toString().trim();
        String categorie=category.getSelectedItem().toString().trim();

        if(numeMancare.isEmpty())
        {
            foodName.setError("Food name is required!");
            foodName.requestFocus();
            return;
        }
        if(cantitate.isEmpty())
        {
            quantity.setError("Quantity in grams is required!");
            quantity.requestFocus();
            return;
        }

//        if(categorie.isEmpty())
//        {
//            category.setError("Category is required!");
//            category.requestFocus();
//            return;
//        }

        database=FirebaseDatabase.getInstance("https://food-calorie-counter-a107a-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference=database.getReference().child("Foods");
        databaseReferenceDiary=database.getReference().child("DiaryFoods");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int ok=0;
                for(DataSnapshot ds:snapshot.getChildren()){
                    food=ds.getValue(Food.class);
                    if(food.getFoodName().equals(numeMancare))
                    {
                        ok=1;
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
                            FoodDiary foodDiary=new FoodDiary(userEmail,strDay,strMonth,strYear,cantitate,numeMancare,categorie,numeBrand,caloriiJurnal,carboJurnal,grasimiJurnal,proteineJurnal,codDeBare);
                            databaseReferenceDiary.push().setValue(foodDiary).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(SearchFoodNameActivity.this,"Food inserted successfully into the diary database!",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(),DiaryActivity.class));
                                    }
                                    else{
                                        Toast.makeText(SearchFoodNameActivity.this,"Food failed insertion into the diary database!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }



                    }

                }
                if(ok==0){
                    Toast.makeText(SearchFoodNameActivity.this, "No food with that name!", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
