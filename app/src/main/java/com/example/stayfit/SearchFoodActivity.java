package com.example.stayfit;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class SearchFoodActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView homeButton,searchByString,searchByBarcode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_food);

        homeButton=(ImageView) findViewById(R.id.homeButtonSearchFood);
        homeButton.setOnClickListener(this);

        searchByBarcode=(ImageView)findViewById(R.id.tv_scan_code);
        searchByBarcode.setOnClickListener(this);

        searchByString=(ImageView)findViewById(R.id.tv_search);
        searchByString.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.homeButtonSearchFood:
                startActivity(new Intent(SearchFoodActivity.this,DiaryActivity.class));
                break;
            case R.id.tv_search:
                startActivity(new Intent(SearchFoodActivity.this,SearchFoodNameActivity.class));
                break;
            case R.id.tv_scan_code:
                startActivity(new Intent(SearchFoodActivity.this,SearchFoodBarcodeActivity.class));
                break;


        }
    }
}
