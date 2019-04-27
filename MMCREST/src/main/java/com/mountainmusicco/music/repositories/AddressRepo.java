package com.mountainmusicco.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{

}
