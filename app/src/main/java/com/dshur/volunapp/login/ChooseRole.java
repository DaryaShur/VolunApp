package com.dshur.volunapp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dshur.volunapp.R;
import com.dshur.volunapp.login.Login;
import com.dshur.volunapp.organizer.OrganizerRegister;
import com.dshur.volunapp.volunteer.VolunteerRegister;

public class ChooseRole extends AppCompatActivity {
    Button volunteerButton, organizerButton;
    TextView mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);

        volunteerButton = findViewById(R.id.volunteerButton);
        organizerButton = findViewById(R.id.organizerButton);
        mLoginButton = findViewById(R.id.loginText);

        organizerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OrganizerRegister.class));
            }
        });

        volunteerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), VolunteerRegister.class));
            }
        });


        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}
