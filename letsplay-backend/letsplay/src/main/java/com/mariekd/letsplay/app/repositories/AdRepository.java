package com.mariekd.letsplay.app.repositories;

import com.mariekd.letsplay.app.entities.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository <Ad, Integer> {

    Optional<Ad> findByTitle (String title);
}
