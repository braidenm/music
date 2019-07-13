package com.mountainmusicco.music.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mountainmusicco.music.entities.User;
import com.mountainmusicco.music.entities.Venue;
import com.mountainmusicco.music.entities.VenueNote;
import com.mountainmusicco.music.repositories.UserRepo;
import com.mountainmusicco.music.repositories.VenueNoteRepo;
import com.mountainmusicco.music.repositories.VenueRepo;

@Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	VenueRepo vRepo;

	@Autowired
	UserRepo rRepo;

	@Autowired
	VenueNoteRepo vnRepo;

	@Override
	public Set<Venue> index(String username) {
		Set<Venue> result = new HashSet<>();
		if (rRepo.findByUserName(username) != null) {
			result.addAll(vRepo.findAll());
		}

		return result;
	}

	@Override
	public Venue show(String username, int id) {

		if (rRepo.findByUserName(username) != null) {
			return vRepo.findById(id).get();
		}

		return null;
	}

	@Override
	public Venue create(String username, Venue mock) {
		User usr = rRepo.findByUserName(username);
		if (usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {

			return vRepo.saveAndFlush(mock);

		}
		return null;
	}

	@Override
	public Venue update(String username, Venue mock, int id) {
		Optional<Venue> temp = vRepo.findById(id);
		User usr = rRepo.findByUserName(username);
		if (usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {
			if (temp.isPresent()) {
				Venue result = temp.get();
				result.setName(mock.getName());
				result.setOwner(mock.getOwner());
				return vRepo.saveAndFlush(result);

			}

		}

		return null;
	}

	@Override
	public void destroy(String username, int id) {

		User usr = rRepo.findByUserName(username);

		if (usr != null && usr.getRole() == "admin") {
			Optional<Venue> op = vRepo.findById(id);
			// delete children first
			if (op.isPresent()) {
				Venue ven = op.get();
				
				List<VenueNote> notes = ven.getNotes();
				
				for(VenueNote note : notes) {
					vnRepo.deleteById(note.getId());
				}
				ven.setNotes(null);
				vRepo.delete(ven);
			} else {
				System.err.println("Not valid Venue");
			}
			
		} else {
			System.err.println("Not Admin or Valid User");
		}

	}

}
