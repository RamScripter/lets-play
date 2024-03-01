package com.mariekd.letsplay.app.repositories;

import com.mariekd.letsplay.app.entities.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository <Ad, Integer> {


}
