package com.mehmetkerem.galleryApp.repository;

import com.mehmetkerem.galleryApp.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
