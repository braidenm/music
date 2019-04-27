package com.mountainmusicco.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.Client;


public interface ClientRepo extends JpaRepository<Client, Integer>{

}
