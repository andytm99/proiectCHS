package com.example.stayfit;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView homeButton,addButton;
    private ListView foodListView;
    private ArrayList<Food> foodListViewArrayList=new ArrayList<>();
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    protected Food food;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_screen);

        homeButton=(ImageView) findViewById(R.id.mainMenuButtonFood);
        homeButton.setOnClickListener(this);

        addButton=(ImageView) findViewById(R.id.addFoodButton);
        addButton.setOnClickListener(this);


        foodListView=(ListView)findViewById(R.id.foodListView);
        database=FirebaseDatabase.getInstance("https://food-calorie-counter-a107a-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference=database.getReference().child("Foods");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds:snapshot.getChildren()){
                    food=ds.getValue(Food.class);
                    foodListViewArrayList.add(food);
                }
                FoodListAdapter adapter= new FoodListAdapter(FoodActivity.this,R.layout.adapter_food_view,foodListViewArrayList);
                foodListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.mainMenuButtonFood:
                startActivity(new Intent(this,MainMenuActivity.class ));
                break;
            case R.id.addFoodButton:
                startActivity(new Intent(this,AddFoodActivity.class ));
                break;

        }

    }

}
