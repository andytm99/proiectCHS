package com.example.stayfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginUser extends AppCompatActivity implements View.OnClickListener{
    private TextView login;
    private TextView back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login=(TextView)findViewById(R.id.loginButton);
        login.setOnClickListener(this);

        back=(TextView)findViewById(R.id.backButtonLogin);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginButton:
                break;

            case R.id.backButtonLogin:
                startActivity(new Intent(this,MainActivity.class ));
                break;
        }

    }
}
