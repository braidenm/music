package com.mountainmusicco.music.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.EventNote;

public interface EventNoteRepo extends JpaRepository<EventNote, Integer> {
	
	List<EventNote> getByEventId(Integer eventId);

}
