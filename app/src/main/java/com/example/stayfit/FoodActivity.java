package com.example.stayfit;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView homeButton,addButton;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_screen);

        homeButton=(ImageView) findViewById(R.id.mainMenuButtonFood);
        homeButton.setOnClickListener(this);

        addButton=(ImageView) findViewById(R.id.addFoodButton);
        addButton.setOnClickListener(this);

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
