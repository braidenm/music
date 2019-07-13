package com.mountainmusicco.music.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mountainmusicco.music.entities.Client;
import com.mountainmusicco.music.entities.ClientNote;
import com.mountainmusicco.music.entities.User;
import com.mountainmusicco.music.repositories.ClientNoteRepo;
import com.mountainmusicco.music.repositories.ClientRepo;
import com.mountainmusicco.music.repositories.UserRepo;

@Service
public class ClientNoteServiceImpl implements ClientNoteService {

	@Autowired
	ClientNoteRepo cnRepo;

	@Autowired
	UserRepo rRepo;
	
	@Override
	public Set<ClientNote> index(String username) {
		Set<ClientNote> result = new HashSet<>();
		if (rRepo.findByUserName(username) != null) {
			result.addAll(cnRepo.findAll());
		}

		return result;
	}

	@Override
	public ClientNote show(String username, Integer id) {

		if (rRepo.findByUserName(username) != null) {
			return cnRepo.findById(id).get();
		}

		return null;
	}

	@Override
	public ClientNote create(String username, ClientNote mock) {
		User usr = rRepo.findByUserName(username);
		if (usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {

			return cnRepo.saveAndFlush(mock);

		}
		return null;
	}

	@Override
	public ClientNote update(String username, ClientNote mock, Integer id) {
		
		Optional<ClientNote> temp = cnRepo.findById(id);
		User usr = rRepo.findByUserName(username);
		
		if (usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {
			if (temp.isPresent()) {
				//TODO need to update client separately 
				//not updating the created or updated date since those happen automatically
				
				ClientNote result = temp.get();
				result.setActive(mock.isActive());
				result.setTitle(mock.getTitle());
				result.setNote(mock.getNote());
				
				return cnRepo.saveAndFlush(result);

			}
		}

		return null;
	}

	@Override
	public void destroy(String username, Integer id) {

		User usr = rRepo.findByUserName(username);

		if (usr != null && usr.getRole() == "admin") {
			Optional<ClientNote> op = cnRepo.findById(id);
			// delete children first
			if (op.isPresent()) {
				ClientNote clNote = op.get();
				
				clNote.setClient(null);
				
				cnRepo.delete(clNote);
			} else {
				System.err.println("Not valid Address");
			}
			
		} else {
			System.err.println("Not Admin or Valid User");
		}

	}

}
