package com.mariekd.letsplay.app.services.implementation;

import com.mariekd.letsplay.app.entities.Ad;
import com.mariekd.letsplay.app.repositories.AdRepository;
import com.mariekd.letsplay.app.services.AdService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    public AdServiceImpl(AdRepository AdRepository) {
        this.adRepository = AdRepository;
    }


    @Override
    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    @Override
    public Ad getAdById(int id) {
        return adRepository.findById(id).orElse(null);
    }

    @Override
    public Ad createAd(Ad ad) {
        return adRepository.save(ad);
    }

    @Override
    public Ad updateAd(int id, Ad ad) {
        if (adRepository.existsById(id)) {
            ad.setId(id);
            return adRepository.save(ad);
        }
        else {
            throw new UsernameNotFoundException("Ad not found");
        }
    }

    @Override
    public void deleteAd(int id) {
        adRepository.deleteById(id);
    }
}
