package com.mariekd.letsplay.app.controllers;

import com.mariekd.letsplay.authentication.controller.AuthController;
import com.mariekd.letsplay.app.entities.MusicianType;
import com.mariekd.letsplay.app.services.MusicianTypeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/musician_types")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class MusicianTypeController {

    @Autowired
    private final MusicianTypeService musicianTypeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @GetMapping
    public List<MusicianType> getAllMusicianTypes() {
        LOGGER.info("Getting all musician types: " + musicianTypeService.getAllMusicianTypes());
        return musicianTypeService.getAllMusicianTypes();
    }

    @GetMapping("/{id}")
    public MusicianType getMusicianTypeById(@PathVariable String id) {
        return musicianTypeService.getMusicianTypeById(id);
    }

    @PostMapping
    public MusicianType createMusicianType(@RequestBody MusicianType musicianType) {
        return musicianTypeService.createMusicianType(musicianType);
    }

    @PutMapping("/{id}")
    public MusicianType updateMusicianType(@PathVariable String id, @RequestBody MusicianType musicianType) {
        return musicianTypeService.updateMusicianType(id, musicianType);
    }

    @DeleteMapping("/{id}")
    public void deleteMusicianType(@PathVariable String id) {
        musicianTypeService.deleteMusicianType(id);
    }

}
