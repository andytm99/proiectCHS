package com.example.stayfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button logout,homeButton,profileButton,goalButton,foodButton,historyButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        logout=(Button)findViewById(R.id.logoutButton);
        logout.setOnClickListener(this);

        homeButton=(Button)findViewById(R.id.homeButtonMenu);
        homeButton.setOnClickListener(this);

        profileButton=(Button)findViewById(R.id.buttonProfileMenu);
        profileButton.setOnClickListener(this);

//        goalButton=(Button)findViewById(R.id.goalButtonMenu);
//        goalButton.setOnClickListener(this);

        foodButton=(Button)findViewById(R.id.foodButtonMenu);
        foodButton.setOnClickListener(this);

        historyButton=(Button)findViewById(R.id.foodHistoryButtonMenu);
        historyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.logoutButton:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainMenuActivity.this,MainActivity.class));
                break;

            case R.id.homeButtonMenu:
                startActivity(new Intent(MainMenuActivity.this,DiaryActivity.class));
                break;

            case R.id.buttonProfileMenu:
                startActivity(new Intent(MainMenuActivity.this,ProfileUser.class));
                break;

//            case R.id.goalButtonMenu:
//                //waiting for goal activity implementation
//                break;

            case R.id.foodButtonMenu:
                //waiting for food activity implementation
                startActivity(new Intent(MainMenuActivity.this,FoodActivity.class));
                break;

            case R.id.foodHistoryButtonMenu:
                startActivity(new Intent(MainMenuActivity.this,CalendarMain.class));
                break;
        }


    }
}
