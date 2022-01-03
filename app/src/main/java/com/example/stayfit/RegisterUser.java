package com.example.stayfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    private TextView back;
    private TextView sign;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        back=(TextView)findViewById(R.id.buttonBackSignUp);
        back.setOnClickListener(this);

        sign=(TextView)findViewById(R.id.buttonSignUp);
        sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonBackSignUp:
                startActivity(new Intent(this,MainActivity.class ));
                break;
            case R.id.buttonSignUp:
                startActivity(new Intent(this,MainActivity.class ));
                System.out.println(" ");
                break;
        }

    }
}
