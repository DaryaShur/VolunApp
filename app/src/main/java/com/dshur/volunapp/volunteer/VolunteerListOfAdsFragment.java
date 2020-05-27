package com.dshur.volunapp.volunteer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dshur.volunapp.R;
import com.dshur.volunapp.adapters.AdRecyclerAdapter;
import com.dshur.volunapp.model.Ad;
import com.dshur.volunapp.viewmodel.AdViewModel;


import java.util.List;

public class VolunteerListOfAdsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private AdViewModel mAdViewModel;
    private AdRecyclerAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_ads, container, false);
        setHasOptionsMenu(true);
        mRecyclerView = view.findViewById(R.id.recyclerView);

        mAdViewModel = ViewModelProviders.of(this).get(AdViewModel.class);
        mAdViewModel.init();
        mAdViewModel.getAds().observe( getViewLifecycleOwner(), new Observer<List<Ad>>() {
            @Override
            public void onChanged(List<Ad> ads) {
                mAdapter.notifyDataSetChanged();
            }
        });


        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        mAdapter = new AdRecyclerAdapter(mAdViewModel.getAds().getValue());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

     @Override
      public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchItem.setActionView(searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
/*
    private void searchByTitle (final String str) {

        adRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        listOfAds.clear();

                        for (DocumentSnapshot doc: task.getResult()) {
                            String title = doc.getString("title").toLowerCase();

                            if (title.contains(str)){
                                Ad ad = new Ad(doc.getString("title"), doc.getString("description"));
                                listOfAds.add(ad);
                            }

                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        AdRecyclerAdapter myAdapter = new AdRecyclerAdapter(listOfAds);
        myAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(myAdapter);



        *//*adRef.whereEqualTo("title".toString().toLowerCase(), str.toLowerCase())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        listOfAds.clear();

                        for (DocumentSnapshot doc: task.getResult()) {
                            Ad ad = new  Ad(doc.getString("title"),
                                    doc.getString("description"));
                            listOfAds.add(ad);
                        }
                        FirestoreRecyclerOptions<Ad> options = new FirestoreRecyclerOptions.Builder<Ad>()
                                .setQuery(adRef, Ad.class)
                                .build();

                        adapter = new AdRecyclerAdapter(options);
                        listOfAdsRecyclerView.setAdapter(adapter);
                        adapter.startListening();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });*//*

    }
*/
}
