package com.mariekd.letsplay.app.controllers;

import com.mariekd.letsplay.app.dto.AdDTO;
import com.mariekd.letsplay.app.dto.mappers.AdMapper;
import com.mariekd.letsplay.app.entities.Ad;
import com.mariekd.letsplay.app.entities.Location;
import com.mariekd.letsplay.app.entities.MusicianType;
import com.mariekd.letsplay.app.entities.Style;
import com.mariekd.letsplay.app.request.AppRequest;
import com.mariekd.letsplay.app.services.implementation.AdServiceImpl;
import com.mariekd.letsplay.app.services.implementation.LocationServiceImpl;
import com.mariekd.letsplay.app.services.implementation.MusicianTypeServiceImpl;
import com.mariekd.letsplay.app.services.implementation.StyleServiceImpl;
import com.mariekd.letsplay.authentication.entities.User;
import com.mariekd.letsplay.authentication.jwt.JwtService;
import com.mariekd.letsplay.authentication.services.implementations.UserServiceImpl;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ads")
@CrossOrigin(maxAge = 3600)
public class AdController {

    private final AdServiceImpl adService;
    private final JwtService jwtService;
    private final UserServiceImpl userService;
    private final MusicianTypeServiceImpl musicianTypeService;
    private final StyleServiceImpl styleService;
    private final LocationServiceImpl locationService;


    public AdController(AdServiceImpl adService, JwtService jwtService, UserServiceImpl userService, MusicianTypeServiceImpl musicianTypeService, StyleServiceImpl styleService, LocationServiceImpl locationService) {
        this.adService = adService;
        this.jwtService = jwtService;
        this.userService = userService;
        this.musicianTypeService = musicianTypeService;
        this.styleService = styleService;
        this.locationService = locationService;
    }

    @GetMapping("/get/all")
    public List<AdDTO> getAllAds() {
        List<Ad> ads = adService.getAllAds();
        return ads.stream().map(AdMapper::toAdDTO).toList();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAdById(@PathVariable int id) {
        Optional<Ad> ad = adService.getAdById(id);
        if (ad.isPresent()){
            return ResponseEntity.ok(AdMapper.toAdDTO(ad.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
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
    public ResponseEntity<?> createAd(@RequestBody AppRequest creatingAd, HttpServletRequest request) {

        SecurityContextHolder.getContext().getAuthentication().getAuthorities();


        String userName;
        User postingUser;

        try {
            userName = jwtService.getUserNameFromJwtToken(getJwtFromRequest(request));
            postingUser = userService.getUserByEmail(userName);
        } catch (JwtException e) {
            throw new AccessDeniedException("Invalid token");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown user");
        }

        Date currentDate = new Date(System.currentTimeMillis());

        final MusicianType adMusicianType = musicianTypeService.findByName(creatingAd.musicianType());
        final Style adStyle = styleService.findByName(creatingAd.style());
        final Location adLocation = locationService.findByName(creatingAd.location());

        Ad ad = new Ad();
        ad.setCreatedAt(Date.from(currentDate.toInstant()));
        ad.setPostedBy(postingUser);
        ad.setTitle(creatingAd.title());
        ad.setSeekingMusicianType(adMusicianType);
        ad.setStyleType(adStyle);
        ad.setLocation(adLocation);
        ad.setDescription(creatingAd.description());
        ad.setImage(creatingAd.image());


        try {
            Optional<Ad> existingAd = adService.getAdByTitle(creatingAd.title());
            if (existingAd.isPresent()) {
                return ResponseEntity.badRequest().body("Ad with this title already exists");
            } else {
                try {
                    adService.createAd(ad);
                    AdDTO adDTO = AdMapper.toAdDTO(ad);
                    return ResponseEntity.ok(adDTO);
                } catch (Exception e) {

                    return ResponseEntity.badRequest().body(e.getMessage());
                }

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
