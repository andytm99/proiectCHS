package com.example.stayfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SearchFoodNameActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backButton;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_food_name_view);

        backButton=(ImageView) findViewById(R.id.backButtonSearchName);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.backButtonSearchName:
                startActivity(new Intent(this,SearchFoodActivity.class));
                break;

        }

    }
}
