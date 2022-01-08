package com.example.stayfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchFoodNameActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backButton;
    private Button addButton;
    private EditText foodName,quantity,category;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_food_name_view);

        backButton=(ImageView) findViewById(R.id.backButtonSearchName);
        backButton.setOnClickListener(this);

        addButton=(Button)findViewById(R.id.buttonAddFoodDiaryToDatabaseName);
        addButton.setOnClickListener(this);

        foodName =(EditText)findViewById(R.id.editTextProductNameSearch);
        quantity=(EditText)findViewById(R.id.editTextQuantityName) ;
        category=(EditText)findViewById(R.id.editTextCategoryName) ;

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
        String categorie=category.getText().toString().trim();

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

        if(categorie.isEmpty())
        {
            category.setError("Category is required!");
            category.requestFocus();
            return;
        }

        database=FirebaseDatabase.getInstance("https://food-calorie-counter-a107a-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference=database.getReference().child("Foods");
        databaseReferenceDiary=database.getReference().child("DiaryFoods");

    }
}
