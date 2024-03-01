package com.mariekd.letsplay.app.controllers;

import com.mariekd.letsplay.app.dto.AdDTO;
import com.mariekd.letsplay.app.dto.mappers.AdMapper;
import com.mariekd.letsplay.app.entities.Ad;
import com.mariekd.letsplay.app.services.implementation.AdServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
@CrossOrigin(maxAge = 3600)
public class AdController {

    private final AdServiceImpl adService;

    public AdController(AdServiceImpl adService) {
        this.adService = adService;
    }

    @GetMapping("/all")
    public List<AdDTO> getAllAds() {
        List<Ad> ads = adService.getAllAds();
        return ads.stream().map(AdMapper::toAdDTO).toList();
    }

    @GetMapping("/{id}")
    public AdDTO getAdById(@PathVariable int id) {
        Ad ad = adService.getAdById(id);
        return AdMapper.toAdDTO(ad);
    }

    @PostMapping("/create")
    public Ad createAd() {
        return null;
    }

    @PutMapping("/update/{id}")
    public Ad updateAd() {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAd() {
    }
}
