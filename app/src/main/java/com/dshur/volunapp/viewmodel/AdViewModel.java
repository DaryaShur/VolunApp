package com.dshur.volunapp.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dshur.volunapp.model.Ad;
import com.dshur.volunapp.repositories.AdRepository;

import java.util.List;

public class AdViewModel extends ViewModel {
    private MutableLiveData<List<Ad>> mAds;
    private AdRepository mRepo;

    public void init() {
        if (mAds != null) {
            return;
        }
        mRepo = AdRepository.getInstance();
        mAds = mRepo.getAds();
    }

    public LiveData<List<Ad>> getAds(){
        return mAds;
    }

    public void addNewAd(final Ad ad) {
        mRepo.addNewAd(ad);
        List<Ad> currentAds = mAds.getValue();
        currentAds.add(ad);
        mAds.postValue(currentAds);
    }

}

