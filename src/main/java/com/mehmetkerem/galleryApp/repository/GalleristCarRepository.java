package com.mehmetkerem.galleryApp.repository;

import com.mehmetkerem.galleryApp.models.GalleristCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleristCarRepository extends JpaRepository<GalleristCar,Long> {
}
