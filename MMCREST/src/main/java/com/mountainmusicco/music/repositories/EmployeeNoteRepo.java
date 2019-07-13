package com.mountainmusicco.music.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.EmployeeNote;

public interface EmployeeNoteRepo extends JpaRepository<EmployeeNote, Integer> {
	
	List<EmployeeNote> getByEmployeeId(Integer employeeId);

}
