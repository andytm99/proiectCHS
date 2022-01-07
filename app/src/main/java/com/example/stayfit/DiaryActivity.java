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
    private ImageView homeButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_categories);

        homeButton=(ImageView) findViewById(R.id.homeButtonDiary);
        homeButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.homeButtonDiary:
                startActivity(new Intent(this,MainMenuActivity.class ));
                break;

        }
    }
}