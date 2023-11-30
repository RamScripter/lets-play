package com.mariekd.letsplay.repositories;

import com.mariekd.letsplay.entities.MusicianType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicianTypeRepository extends JpaRepository<MusicianType, String> {
}
