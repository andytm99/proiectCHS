package com.example.stayfit;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ViewHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView homeButton;
    private ListView foodHistoryListView;
    private ArrayList<FoodDiary> foodHistoryListViewArrayList=new ArrayList<>();
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    protected FoodDiary food;

//    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//    Calendar c = Calendar.getInstance();
//    String date = sdf.format(c.getTime());


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_screen);

        homeButton=(ImageView) findViewById(R.id.mainMenuButtonHistory);
        homeButton.setOnClickListener(this);

        foodHistoryListView=(ListView)findViewById(R.id.foodHistoryListView);
        database=FirebaseDatabase.getInstance("https://food-calorie-counter-a107a-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference=database.getReference().child("DiaryFoods");

        Bundle extras = getIntent().getExtras();
        String date = extras.getString("date");
        String[] parts = date.split("/");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds:snapshot.getChildren()){
                    food=ds.getValue(FoodDiary.class);
                    if(food.getDay().equals(day) && food.getMonth().equals(month) && food.getYear().equals(year)) {
                        foodHistoryListViewArrayList.add(food);
                    }
                }
                ViewHistoryListAdapter adapter= new ViewHistoryListAdapter(ViewHistoryActivity.this,R.layout.adapter_history_view,foodHistoryListViewArrayList);
                foodHistoryListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainMenuButtonHistory:
                startActivity(new Intent(this, MainMenuActivity.class));
                break;
        }

        switch (v.getId()) {
            case R.id.buttonSearchFoodHistory:
                startActivity(new Intent(this, ViewHistoryActivity.class));
                break;
        }
    }

}
