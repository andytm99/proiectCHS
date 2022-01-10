package com.example.stayfit;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ViewHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView homeButton;
    private ListView foodHistoryListView;
    private ArrayList<FoodDiary> foodHistoryListViewArrayList=new ArrayList<>();
    private DatabaseReference databaseReference,databaseReferenceUsers;
    private FirebaseDatabase database;
    protected FoodDiary food;
    private TextView tvTarget,tvSum;
    protected User userApp;

//    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//    Calendar c = Calendar.getInstance();
//    String date = sdf.format(c.getTime());


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_screen);

        Bundle extras = getIntent().getExtras();
        String date = extras.getString("date");
        String[] parts = date.split("/");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        homeButton=(ImageView) findViewById(R.id.mainMenuButtonHistory);
        homeButton.setOnClickListener(this);

        tvTarget=(TextView)findViewById(R.id.editTextFoodTargetCaloriiHistory);
        tvSum=(TextView)findViewById(R.id.editTextSumCaloriiHistory);

        foodHistoryListView=(ListView)findViewById(R.id.foodHistoryListView);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            String userEmail=user.getEmail();
            database = FirebaseDatabase.getInstance("https://food-calorie-counter-a107a-default-rtdb.europe-west1.firebasedatabase.app");
            databaseReference = database.getReference().child("DiaryFoods");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int sumaCalorii=0;
                    int calorii=0;
                    float fCalorii=0;

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        food = ds.getValue(FoodDiary.class);
                        if (food.getDay().equals(day) && food.getMonth().equals(month) && food.getYear().equals(year) && food.getEmail().equals(userEmail)) {
                            foodHistoryListViewArrayList.add(food);
                            fCalorii=Float.parseFloat(food.getCalories());
                            calorii=(int) fCalorii;
                            sumaCalorii=sumaCalorii+calorii;
                        }
                    }
                    ViewHistoryListAdapter adapter = new ViewHistoryListAdapter(ViewHistoryActivity.this, R.layout.adapter_history_view, foodHistoryListViewArrayList);
                    foodHistoryListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    String strSumaCalorii=String.valueOf(sumaCalorii);
                    tvSum.setText(strSumaCalorii);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            //pentru calcularea targetului de calorii
            databaseReferenceUsers=database.getReference().child("Users");
            databaseReferenceUsers.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ds:snapshot.getChildren()) {
                        userApp=ds.getValue(User.class);
                        if(userApp.getEmail().equals(userEmail)){
                            double BMR;
                            java.util.Calendar calendar= java.util.Calendar.getInstance(TimeZone.getDefault());
                            int year=calendar.get(Calendar.YEAR);
                            int iInaltime=Integer.parseInt(userApp.getHeight());
                            int iGreutate=Integer.parseInt(userApp.getWeight());
                            int iVarsta=year - Integer.parseInt(userApp.getYear());
                            BMR=88.362+(13.397*iGreutate)+(4.799*iInaltime)+(5.677*iVarsta);
                            double caloriiTarget=BMR*1.2;
                            int iCaloriiTarget=(int)caloriiTarget;
                            String strCaloriiTarget=String.valueOf(iCaloriiTarget);
                            tvTarget.setText(strCaloriiTarget);
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
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
