package com.dshur.volunapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dshur.volunapp.R;
import com.dshur.volunapp.model.Ad;

import java.util.ArrayList;
import java.util.List;

public class AdRecyclerAdapter extends RecyclerView.Adapter<AdRecyclerAdapter.AdHolder>  {
    private List<Ad> mAds = new ArrayList<>();

    public AdRecyclerAdapter(List<Ad> listOfAds) {
        this.mAds = listOfAds;
    }

    @NonNull
    @Override
    public AdHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_item, parent, false);
        return new AdHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdHolder holder, int position) {
        Ad ad = mAds.get(position);

        holder.titleTextView.setText(ad.getTitle());
        holder.descriptionTextView.setText(ad.getDescription());
    }

    @Override
    public int getItemCount() {
        return mAds.size();
    }


    public static class AdHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, descriptionTextView;
        View view;

        public AdHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);

            /*view = itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });*/

        }
    }

}
