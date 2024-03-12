package com.mariekd.letsplay.app.repositories;

import com.mariekd.letsplay.app.entities.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository <Ad, Integer> {

    @Modifying
    @Query("DELETE FROM Ad a WHERE a.id = :id")
    void deleteById(int id);

    Optional<Ad> findByTitle (String title);
}
