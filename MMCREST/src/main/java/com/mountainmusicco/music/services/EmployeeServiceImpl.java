package com.mountainmusicco.music.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mountainmusicco.music.entities.Employee;
import com.mountainmusicco.music.entities.EmployeeNote;
import com.mountainmusicco.music.entities.Event;
import com.mountainmusicco.music.entities.User;
import com.mountainmusicco.music.repositories.EmployeeNoteRepo;
import com.mountainmusicco.music.repositories.EmployeeRepo;
import com.mountainmusicco.music.repositories.EventRepo;
import com.mountainmusicco.music.repositories.UserRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo eRepo;

	@Autowired
	UserRepo rRepo;

	@Autowired
	EmployeeNoteRepo enRepo;

	@Autowired
	EventRepo evRepo;

	@Override
	public Set<Employee> index(String username) {
		Set<Employee> result = new HashSet<>();
		if (rRepo.findByUserName(username) != null) {
			result.addAll(eRepo.findAll());
		}

		return result;
	}

	@Override
	public Employee show(String username, int id) {

		if (rRepo.findByUserName(username) != null) {
			return eRepo.findById(id).get();
		}

		return null;
	}

	@Override
	public Employee create(String username, Employee mock) {
		User usr = rRepo.findByUserName(username);
		if (usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {

			return eRepo.saveAndFlush(mock);

		}
		return null;
	}

	@Override
	public Employee update(String username, Employee mock, int id) {
		Optional<Employee> temp = eRepo.findById(id);
		User usr = rRepo.findByUserName(username);
		if (usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {
			if (temp.isPresent()) {
				// TODO need to update notes separately
				// TODO need to update user separately
				// TODO need to update Events separately
				Employee result = temp.get();
				result.setEmail(mock.getEmail());
				result.setPayRate(mock.getPayRate());
				result.setTitle(mock.getTitle());

				return eRepo.saveAndFlush(result);

			}

		}

		return null;
	}

	@Override
	public void destroy(String username, int id) {

		User usr = rRepo.findByUserName(username);

		if (usr != null && usr.getRole() == "admin") {

			Optional<Employee> op = eRepo.findById(id);

			// need to delete the children first
			if (op.isPresent()) {
				Employee emp = op.get();

				List<EmployeeNote> notes = emp.getNotes();
				for (EmployeeNote note : notes) {
					enRepo.deleteById(note.getId());
				}
				emp.setNotes(null);

				List<Event> events = emp.getEvents();

				for (Event event : events) {
					evRepo.deleteById(event.getId());
				}
				emp.setEvents(null);

				Optional<User> uOp = rRepo.findById(emp.getUser().getId());

				if (uOp.isPresent()) {
					User user = uOp.get();
					user.setEmployee(null);
					rRepo.saveAndFlush(user);
				}
				emp.setUser(null);

				eRepo.delete(emp);
				;
			} else {

				System.err.println("No Employee exists");
			}

		} else {
			System.err.println("Not admin or Not valid");
		}
		

	}

}
