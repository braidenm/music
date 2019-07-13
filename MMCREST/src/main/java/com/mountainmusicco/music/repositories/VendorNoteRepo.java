package com.mountainmusicco.music.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.VendorNote;

public interface VendorNoteRepo extends JpaRepository<VendorNote, Integer> {
	
	List<VendorNote> getByVendorId(Integer vendorId);

}
