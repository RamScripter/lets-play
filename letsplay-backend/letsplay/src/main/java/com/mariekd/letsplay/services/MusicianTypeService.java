package com.mariekd.letsplay.services;

import com.mariekd.letsplay.entities.MusicianType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MusicianTypeService {
    List<MusicianType> getAllMusicianTypes();
    MusicianType getMusicianTypeById(String id);
    MusicianType createMusicianType(MusicianType musicianType);
    MusicianType updateMusicianType(String id, MusicianType musicianType);
    void deleteMusicianType(String id);
}
