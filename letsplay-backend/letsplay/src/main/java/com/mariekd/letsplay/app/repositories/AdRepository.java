package com.mariekd.letsplay.app.repositories;

import com.mariekd.letsplay.app.entities.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository <Ad, Integer> {

    @Query("SELECT COUNT(a) > 0 FROM Ad a WHERE a.title = :title")
    Boolean existsAdByTitle (@Param("title") String title);

}
