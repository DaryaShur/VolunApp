package com.dshur.volunapp.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dshur.volunapp.model.Ad;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdRepository {

    private static AdRepository instance;
    private ArrayList<Ad> dataSet = new ArrayList<>();
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    MutableLiveData<List<Ad>> data = new MutableLiveData<>();


    public static AdRepository getInstance() {
        if (instance == null) {
            instance = new AdRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Ad>> getAds(){
        setAds();
        data.setValue(dataSet);
        return data;
    }

    private void setAds() {

        fStore.collection("ListOfAds").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for (DocumentSnapshot doc: task.getResult()) {
                            dataSet.add(new Ad(doc.getString("title"),
                                    doc.getString("description")));
                        }
                        data.postValue(dataSet);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("AdRepository", "onFailure", e);
                    }
                });

    }

    public void addNewAd(Ad ad){
        fStore.collection("ListOfAds").add(ad);
    }

}


/*fStore.collection("ListOfAds").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot documentSnapshot: list) {
                        dataSet.add(documentSnapshot.toObject(Ad.class));
                    }
                    data.postValue(dataSet);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("AdRepository", "onFailure", e);
            }
        });*/