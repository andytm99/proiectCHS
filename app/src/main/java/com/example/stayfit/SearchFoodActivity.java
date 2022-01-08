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

public class SearchFoodActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView homeButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_food);

        homeButton=(ImageView) findViewById(R.id.homeButtonSearchFood);
        homeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.homeButtonSearchFood:
                startActivity(new Intent(SearchFoodActivity.this,DiaryActivity.class));
                break;


        }
    }
}
