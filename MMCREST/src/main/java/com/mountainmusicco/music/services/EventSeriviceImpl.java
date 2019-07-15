package com.mountainmusicco.music.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mountainmusicco.music.entities.Client;
import com.mountainmusicco.music.entities.Event;
import com.mountainmusicco.music.entities.EmployeeNote;
import com.mountainmusicco.music.entities.Event;
import com.mountainmusicco.music.entities.User;
import com.mountainmusicco.music.repositories.EmployeeNoteRepo;
import com.mountainmusicco.music.repositories.EventRepo;
import com.mountainmusicco.music.repositories.UserRepo;

@Service
public class EventSeriviceImpl implements EventService {

	@Autowired
	EventRepo eRepo;

	@Autowired
	UserRepo rRepo;

	@Autowired
	EmployeeNoteRepo enRepo;

	@Override
	public Set<Event> index(String username) {
		Set<Event> result = new HashSet<>();
		if (rRepo.findByUserName(username) != null) {
			result.addAll(eRepo.findAll());
		}

		return result;
	}

	@Override
	public Event show(String username, int id) {

		if (rRepo.findByUserName(username) != null) {
			return eRepo.findById(id).get();
		}

		return null;
	}

	@Override
	public Event create(String username, Event mock) {
		User usr = rRepo.findByUserName(username);
		if (usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {

			return eRepo.saveAndFlush(mock);

		}
		return null;
	}

	@Override
	public Event update(String username, Event mock, int id) {
		Optional<Event> temp = eRepo.findById(id);
		User usr = rRepo.findByUserName(username);
		if (usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {
			if (temp.isPresent()) {

				Event result = temp.get();

				
				return eRepo.saveAndFlush(result);

			}

		}

		return null;
	}

	@Override
	public void destroy(String username, int id) {

		User usr = rRepo.findByUserName(username);

		if (usr != null && usr.getRole() == "admin") {

			Optional<Event> op = eRepo.findById(id);

			// need to delete the children first
			if (op.isPresent()) {
				Event emp = op.get();

			} else {

				System.err.println("No Event exists");
			}

		} else {
			System.err.println("Not admin or Not valid");
		}

	}

}
