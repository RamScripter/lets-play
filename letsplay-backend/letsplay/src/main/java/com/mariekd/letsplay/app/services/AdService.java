package com.mariekd.letsplay.app.services;

import com.mariekd.letsplay.app.entities.Ad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdService {
    List<Ad> getAllAds();

    Ad getAdById(int id);

    Ad createAd(Ad ad);

    Ad updateAd(int id, Ad ad);

    void deleteAd(int id);
}
