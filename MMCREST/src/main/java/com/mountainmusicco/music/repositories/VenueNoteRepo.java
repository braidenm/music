package com.mountainmusicco.music.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.VenueNote;

public interface VenueNoteRepo extends JpaRepository<VenueNote, Integer> {

	List<VenueNote> getByVenueId(Integer venueId);
}
