package com.example.stayfit;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DiaryActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView homeButton,breakfastAdd,lunchAdd,dinnerAdd,snacksAdd;
    private ListView breakfastLV,lunchLV,dinnerLV,snacksLV;
    private DatabaseReference databaseReference,databaseReferenceUsers;
    private FirebaseDatabase database;
    protected FoodDiary foodDiary;
    protected User userApp;
    private TextView tvTarget,tvSum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ArrayList<FoodDiary> breakfastLVArrayList=new ArrayList<>();
        ArrayList<FoodDiary> lunchLVArrayList=new ArrayList<>();
        ArrayList<FoodDiary> dinnerLVArrayList=new ArrayList<>();
        ArrayList<FoodDiary> snacksLVArrayList=new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_categories);

        homeButton=(ImageView) findViewById(R.id.homeButtonDiary);
        homeButton.setOnClickListener(this);

        breakfastAdd=(ImageView) findViewById(R.id.imageViewAddBreakfast);
        breakfastAdd.setOnClickListener(this);

        lunchAdd=(ImageView) findViewById(R.id.imageViewAddLunch);
        lunchAdd.setOnClickListener(this);

        dinnerAdd=(ImageView) findViewById(R.id.imageViewAddDinner);
        dinnerAdd.setOnClickListener(this);

        snacksAdd=(ImageView) findViewById(R.id.imageViewAddSnacks);
        snacksAdd.setOnClickListener(this);

        tvTarget=(TextView)findViewById(R.id.editTextFoodTargetCalorii);
        tvSum=(TextView)findViewById(R.id.editTextSumCalorii);

        breakfastLV=(ListView)findViewById(R.id.breakfastListView);
        lunchLV=(ListView)findViewById(R.id.lunchListView);
        dinnerLV=(ListView)findViewById(R.id.dinnerListView);
        snacksLV=(ListView)findViewById(R.id.snacksListView);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String userEmail=user.getEmail();
        database= FirebaseDatabase.getInstance("https://food-calorie-counter-a107a-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference=database.getReference().child("DiaryFoods");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sumaCalorii=0;
                int calorii=0;
                float fCalorii=0;
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    Calendar calendar=Calendar.getInstance(TimeZone.getDefault());
                    int day=calendar.get(Calendar.DATE);
                    int month=calendar.get(Calendar.MONTH)+1;
                    int year=calendar.get(Calendar.YEAR);
                    String strDay=String.valueOf(day);
                    String strMonth= String.valueOf(month);
                    String strYear=String.valueOf(year);
                    foodDiary=ds.getValue(FoodDiary.class);
                    if((foodDiary.getCategory().equals("Breakfast"))&&(foodDiary.getDay().equals(strDay))&&(foodDiary.getMonth().equals(strMonth))&&(foodDiary.getYear().equals(strYear))&&(foodDiary.getEmail().equals(userEmail)))
                    {
                        breakfastLVArrayList.add(foodDiary);
                        fCalorii=Float.parseFloat(foodDiary.getCalories());
                        calorii=(int) fCalorii;
                        sumaCalorii=sumaCalorii+calorii;
                    }
                    else
                        if((foodDiary.getCategory().equals("Lunch"))&&(foodDiary.getDay().equals(strDay))&&(foodDiary.getMonth().equals(strMonth))&&(foodDiary.getYear().equals(strYear))&&(foodDiary.getEmail().equals(userEmail))){
                            lunchLVArrayList.add(foodDiary);
                            fCalorii=Float.parseFloat(foodDiary.getCalories());
                            calorii=(int) fCalorii;
                            sumaCalorii=sumaCalorii+calorii;
                        }
                        else
                            if((foodDiary.getCategory().equals("Dinner"))&&(foodDiary.getDay().equals(strDay))&&(foodDiary.getMonth().equals(strMonth))&&(foodDiary.getYear().equals(strYear))&&(foodDiary.getEmail().equals(userEmail))){
                                dinnerLVArrayList.add(foodDiary);
                                fCalorii=Float.parseFloat(foodDiary.getCalories());
                                calorii=(int) fCalorii;
                                sumaCalorii=sumaCalorii+calorii;
                            }
                            else
                                if((foodDiary.getCategory().equals("Snacks"))&&(foodDiary.getDay().equals(strDay))&&(foodDiary.getMonth().equals(strMonth))&&(foodDiary.getYear().equals(strYear))&&(foodDiary.getEmail().equals(userEmail))){
                                    snacksLVArrayList.add(foodDiary);
                                    fCalorii=Float.parseFloat(foodDiary.getCalories());
                                    calorii=(int) fCalorii;
                                    sumaCalorii=sumaCalorii+calorii;
                                }
                }

                DiaryActivityAdapter adapterBreakfast =new DiaryActivityAdapter(DiaryActivity.this,R.layout.adapter_diary_view,breakfastLVArrayList);
                breakfastLV.setAdapter(adapterBreakfast);
                adapterBreakfast.notifyDataSetChanged();

                DiaryActivityAdapter adapterLunch= new DiaryActivityAdapter(DiaryActivity.this,R.layout.adapter_diary_view,lunchLVArrayList);
                lunchLV.setAdapter(adapterLunch);
                adapterLunch.notifyDataSetChanged();

                DiaryActivityAdapter adapterDinner= new DiaryActivityAdapter(DiaryActivity.this,R.layout.adapter_diary_view,dinnerLVArrayList);
                dinnerLV.setAdapter(adapterDinner);
                adapterDinner.notifyDataSetChanged();

                DiaryActivityAdapter adapterSnacks=new DiaryActivityAdapter(DiaryActivity.this,R.layout.adapter_diary_view,snacksLVArrayList);
                snacksLV.setAdapter(adapterSnacks);
                adapterSnacks.notifyDataSetChanged();

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
                            Calendar calendar=Calendar.getInstance(TimeZone.getDefault());
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
        switch(v.getId()){
            case R.id.homeButtonDiary:
                startActivity(new Intent(this,MainMenuActivity.class ));
                break;

            case R.id.imageViewAddBreakfast:
                startActivity(new Intent(this,SearchFoodActivity.class));
                break;

            case R.id.imageViewAddLunch:
                startActivity(new Intent(this,SearchFoodActivity.class));
                break;

            case R.id.imageViewAddDinner:
                startActivity(new Intent(this,SearchFoodActivity.class));
                break;

            case R.id.imageViewAddSnacks:
                startActivity(new Intent(this,SearchFoodActivity.class));
                break;

        }
    }
}
