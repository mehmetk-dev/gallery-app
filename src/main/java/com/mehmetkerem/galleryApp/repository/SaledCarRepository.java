package com.mehmetkerem.galleryApp.repository;

import com.mehmetkerem.galleryApp.models.SaledCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaledCarRepository extends JpaRepository<SaledCar,Long> {
}
