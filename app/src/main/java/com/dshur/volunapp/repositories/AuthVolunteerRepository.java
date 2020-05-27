package com.dshur.volunapp.repositories;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.dshur.volunapp.Constants.USERS;

public class AuthVolunteerRepository {
    private FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private CollectionReference userRef = fStore.collection(USERS);

    public void login(String email, String password) {
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //String userID = fAuth.getCurrentUser().getUid();
                }else {
                    //Toast.makeText(AuthVolunteerRepository.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

   /* public void register(String fullName, String email, String password, String phone) {

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

    }*/

}
