package com.mariekd.letsplay.app.controllers;

import com.mariekd.letsplay.app.dto.AdDTO;
import com.mariekd.letsplay.app.dto.mappers.AdMapper;
import com.mariekd.letsplay.app.entities.Ad;
import com.mariekd.letsplay.app.entities.Location;
import com.mariekd.letsplay.app.entities.MusicianType;
import com.mariekd.letsplay.app.entities.Style;
import com.mariekd.letsplay.app.request.AppRequest;
import com.mariekd.letsplay.app.services.implementation.AdServiceImpl;
import com.mariekd.letsplay.authentication.entities.User;
import com.mariekd.letsplay.authentication.jwt.JwtService;
import com.mariekd.letsplay.authentication.services.implementations.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
@CrossOrigin(maxAge = 3600)
public class AdController {

    private final AdServiceImpl adService;
    private final JwtService jwtService;
    private final UserServiceImpl userService;

    public AdController(AdServiceImpl adService, JwtService jwtService, UserServiceImpl userService) {
        this.adService = adService;
        this.jwtService = jwtService;
        this.userService = userService;
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

    private String getJwtFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<?> createAd(@RequestBody AppRequest creatingAd, HttpServletRequest request) { // Utiliser des Strings et récupérer les infos puis ad builder ?
        final String UserName = jwtService.getUserNameFromJwtToken(getJwtFromRequest(request));
        final User postingUser = userService.getUserByEmail(UserName);

        LocalDate localDate = LocalDate.now();
        final Date currentDate = new Date(localDate.atStartOfDay()).toEpochSecond() * 1000);

        //TODO : Générer la date au format java.util.Date et pas LocalDate
        //TODO : créer les repositories et services pour les entités MusicianType, Style et Location
        //TODO : récupérer les objets MusicianType, Style et Location depuis les repositories (getByName)

        final MusicianType musicianType = MusicianTypeRepository.getByName(creatingAd.musicianType());
        final Style style = StyleRepository.getByName(creatingAd.style());
        final Location location = LocationRepository.getByName(creatingAd.location());

        Ad ad = new Ad();
        ad.setCreatedAt(Date.from(LocalDate.now()));
        ad.setPostedBy(postingUser);
        ad.setTitle(creatingAd.title());
        ad.setSeekingMusicianType(creatingAd.musicianType());
        ad.setStyleType(creatingAd.style());
        ad.setLocation(creatingAd.location());
        ad.setDescription(creatingAd.description());
        ad.setImage(creatingAd.image());


        try {
            if (!adService.existsAdByTitle(creatingAd.title())) {
                return ResponseEntity.badRequest().body("Ad with this title already exists");
            } else {
                return ResponseEntity.ok(adService.createAd(ad));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating ad");
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')") //TODO : créer deux routes : générale pour les admins, ne peut modifier que SES annonces pour les users
    @PutMapping("/update/{id}")
    public Ad updateAd() {
        return null;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')") //TODO : créer deux routes : générale pour les admins, ne peut supprimer que SES annonces pour les users
    @DeleteMapping("/delete/{id}")
    public void deleteAd() {
    }
}
