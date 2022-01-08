package com.example.stayfit;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DiaryActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView homeButton,breakfastAdd,lunchAdd,dinnerAdd,snacksAdd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
