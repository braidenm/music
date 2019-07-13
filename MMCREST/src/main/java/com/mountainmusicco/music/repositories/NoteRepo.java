package com.mountainmusicco.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.ClientNote;

public interface NoteRepo extends JpaRepository<ClientNote, Integer> {

}
