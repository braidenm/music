package com.mountainmusicco.music.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mountainmusicco.music.entities.Client;
import com.mountainmusicco.music.entities.Client;
import com.mountainmusicco.music.entities.User;
import com.mountainmusicco.music.repositories.ClientNoteRepo;
import com.mountainmusicco.music.repositories.ClientRepo;
import com.mountainmusicco.music.repositories.UserRepo;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepo cRepo;

	@Autowired
	UserRepo rRepo;
	
	@Override
	public Set<Client> index(String username) {
		Set<Client> result = new HashSet<>();
		if (rRepo.findByUserName(username) != null) {
			result.addAll(cRepo.findAll());
		}

		return result;
	}

	@Override
	public Client show(String username, Integer id) {

		if (rRepo.findByUserName(username) != null) {
			return cRepo.findById(id).get();
		}

		return null;
	}

	@Override
	public Client create(String username, Client mock) {
		User usr = rRepo.findByUserName(username);
		if (usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {

			return cRepo.saveAndFlush(mock);

		}
		return null;
	}

	@Override
	public Client update(String username, Client mock, Integer id) {
		
		Optional<Client> temp = cRepo.findById(id);
		User usr = rRepo.findByUserName(username);
		
		if (usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {
			if (temp.isPresent()) {
				//TODO need to update client separately 
				//not updating the created or updated date since those happen automatically
				
				Client result = temp.get();
				result.setEmail(mock.getEmail());
				result.setFianceeFname(mock.getFianceeFname());
				result.setFianceeLname(mock.getFianceeLname());
				result.setFname(mock.getFname());
				result.setLname(mock.getLname());
				
				return cRepo.saveAndFlush(result);

			}
		}

		return null;
	}

	@Override
	public void destroy(String username, Integer id) {

		User usr = rRepo.findByUserName(username);

		if (usr != null && usr.getRole() == "admin") {
			Optional<Client> op = cRepo.findById(id);
			// delete children first
			if (op.isPresent()) {
				Client clNote = op.get();
				
				
				cRepo.delete(clNote);
			} else {
				System.err.println("Not valid Address");
			}
			
		} else {
			System.err.println("Not Admin or Valid User");
		}

	}

}
