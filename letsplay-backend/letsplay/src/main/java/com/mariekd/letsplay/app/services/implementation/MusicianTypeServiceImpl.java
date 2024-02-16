package com.mariekd.letsplay.app.services.implementation;

import com.mariekd.letsplay.app.entities.MusicianType;
import com.mariekd.letsplay.app.services.MusicianTypeService;
import com.mariekd.letsplay.app.repositories.MusicianTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicianTypeServiceImpl implements MusicianTypeService {

    @Autowired
    private final MusicianTypeRepository musicianTypeRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<MusicianType> getAllMusicianTypes() {
            return musicianTypeRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public MusicianType getMusicianTypeById(String id) {
            return musicianTypeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MusicianType createMusicianType(MusicianType musicianType) {
            return musicianTypeRepository.save(musicianType);
    }

    @Override
    public MusicianType updateMusicianType(String id, MusicianType musicianType) {
        if (musicianTypeRepository.existsById(id)) {
            musicianType.setId(Long.valueOf(id));
            return musicianTypeRepository.save(musicianType);
        }
        else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void deleteMusicianType(String id) {
        musicianTypeRepository.deleteById(id);
    }
}
