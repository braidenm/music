package com.mountainmusicco.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.Vendor;

public interface VendorRepo extends JpaRepository<Vendor, Integer> {

}
