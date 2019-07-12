package com.mountainmusicco.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.Venue;

public interface VenueRepo extends JpaRepository<Venue, Integer> {
	

}
