package com.dshur.volunapp.volunteer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dshur.volunapp.login.Login;
import com.dshur.volunapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class VolunteerRegister extends AppCompatActivity {
    public static final String TAG ="TAG";
    public static final String role ="volunteer";
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterButton;
    TextView mLoginButton;
    ProgressBar progressBarRegister;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_volunteer);

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegisterButton = findViewById(R.id.registerButton);
        mLoginButton = findViewById(R.id.loginText);
        progressBarRegister = findViewById(R.id.progressBarRegister);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        mRegisterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String phone = mPhone.getText().toString();
                final String fullName = mFullName.getText().toString();

                /*if(fAuth.getCurrentUser() != null){
                    Toast.makeText(VolunteerRegister.this, "User has already registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), VolunteerMainActivity.class));
                    finish();
                }*/

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Passsword must be >= 6 characters");
                    return;
                }

                progressBarRegister.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(VolunteerRegister.this, "User created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("fullName", fullName);
                            user.put("phone", phone);
                            user.put("email", email);
                            user.put("role", role);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user profile is created for " + userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), VolunteerMainActivity.class));
                        }else {
                            Toast.makeText(VolunteerRegister.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarRegister.setVisibility(View.GONE);
                        }
                    }
                });

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
