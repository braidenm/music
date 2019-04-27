package com.mountainmusicco.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.Note;

public interface NoteRepo extends JpaRepository<Note, Integer> {

}
