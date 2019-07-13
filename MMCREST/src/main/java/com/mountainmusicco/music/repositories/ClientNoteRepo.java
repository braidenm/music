package com.mountainmusicco.music.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.ClientNote;

public interface ClientNoteRepo extends JpaRepository<ClientNote, Integer> {
	
	List<ClientNote> getByClientId(Integer clientId);

}
