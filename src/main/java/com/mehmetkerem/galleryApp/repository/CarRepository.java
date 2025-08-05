package com.mehmetkerem.galleryApp.repository;

import com.mehmetkerem.galleryApp.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
