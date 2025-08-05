package com.mehmetkerem.galleryApp.repository;

import com.mehmetkerem.galleryApp.models.Gallerist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleristRepository extends JpaRepository<Gallerist,Long> {
    boolean existsByAddress_Id(Long addressId);
}
