package com.example.stayfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ProfileUser extends AppCompatActivity implements View.OnClickListener {

    private TextView back;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    protected User user;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        back=(TextView)findViewById(R.id.buttonBackProfile);
        back.setOnClickListener(this);

        tv1=(TextView)findViewById(R.id.editTextEmail);
        tv2=(TextView)findViewById(R.id.spinnerDOBDay);
        tv3=(TextView)findViewById(R.id.spinnerDOBMonth);
        tv4=(TextView)findViewById(R.id.spinnerDOBYear);
        tv5=(TextView)findViewById(R.id.editTextHeightCm);
        tv6=(TextView)findViewById(R.id.editTextWeight);
        tv7=(TextView)findViewById(R.id.editTextTargetWeight);

        database = FirebaseDatabase.getInstance("https://food-calorie-counter-a107a-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = database.getReference().child("Users");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ds:snapshot.getChildren()) {
                        user = ds.getValue(User.class);
                        tv1.setText(user.getEmail());
                        tv2.setText(user.getDay());
                        tv3.setText(user.getMonth());
                        tv4.setText(user.getYear());
                        tv5.setText(user.getHeight());
                        tv6.setText(user.getWeight());
                        tv7.setText(user.getTargetWeight());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonBackProfile:
                startActivity(new Intent(this,MainActivity.class ));
                break;
        }

    }
}
