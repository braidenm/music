package com.mountainmusicco.music.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mountainmusicco.music.entities.Address;
import com.mountainmusicco.music.entities.User;
import com.mountainmusicco.music.repositories.AddressRepo;
import com.mountainmusicco.music.repositories.UserRepo;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepo aRepo;

	@Autowired
	UserRepo rRepo;

	

	@Override
	public Set<Address> index(String username) {
		Set<Address> result = new HashSet<>();
		if (rRepo.findByUserName(username) != null) {
			result.addAll(aRepo.findAll());
		}

		return result;
	}

	@Override
	public Address show(String username, Integer id) {

		if (rRepo.findByUserName(username) != null) {
			return aRepo.findById(id).get();
		}

		return null;
	}

	@Override
	public Address create(String username, Address mock) {
		User usr = rRepo.findByUserName(username);
		if (usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {

			return aRepo.saveAndFlush(mock);

		}
		return null;
	}

	@Override
	public Address update(String username, Address mock, Integer id) {
		Optional<Address> temp = aRepo.findById(id);
		User usr = rRepo.findByUserName(username);
		if (usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {
			if (temp.isPresent()) {
				Address result = temp.get();
				result.setCity(mock.getCity());
				result.setState(mock.getState());
				result.setStreet(mock.getStreet());
				result.setStreet2(mock.getStreet2());
				result.setZip(mock.getZip());
				return aRepo.saveAndFlush(result);

			}
		}

		return null;
	}

	@Override
	public void destroy(String username, Integer id) {

		User usr = rRepo.findByUserName(username);

		if (usr != null && usr.getRole() == "admin") {
			Optional<Address> op = aRepo.findById(id);
			// delete children first
			if (op.isPresent()) {
				Address ven = op.get();
				
				aRepo.delete(ven);
			} else {
				System.err.println("Not valid Address");
			}
			
		} else {
			System.err.println("Not Admin or Valid User");
		}

	}

}
