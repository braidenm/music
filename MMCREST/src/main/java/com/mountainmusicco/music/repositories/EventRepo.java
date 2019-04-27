package com.mountainmusicco.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.Event;


public interface EventRepo extends JpaRepository<Event, Integer> {

}
