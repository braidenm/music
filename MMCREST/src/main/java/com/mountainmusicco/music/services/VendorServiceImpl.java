package com.mountainmusicco.music.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mountainmusicco.music.entities.User;
import com.mountainmusicco.music.entities.Vendor;
import com.mountainmusicco.music.repositories.UserRepo;
import com.mountainmusicco.music.repositories.VendorRepo;
import com.mountainmusicco.music.repositories.VenueRepo;

@Service
public class VendorServiceImpl implements VendorService {
	
	@Autowired
	VendorRepo vRepo;
	
	@Autowired
	UserRepo rRepo;


	@Override
	public Set<Vendor> index(String username) {
		Set<Vendor> result = new HashSet<>();
		if(rRepo.findByUserName(username) != null) {
			result.addAll(vRepo.findAll());
		}
		
		return result;
	}

	@Override
	public Vendor show(String username, int id) {
		
		if(rRepo.findByUserName(username) != null) {
			return vRepo.findById(id).get();
		}
		
		return null;
	}

	@Override
	public Vendor create(String username, Vendor mock) {
		User usr = rRepo.findByUserName(username);
		if( usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {
			
			return vRepo.saveAndFlush(mock);
			
		}
		return null;
	}

	@Override
	public Vendor update(String username, Vendor mock, int id) {
		Optional<Vendor> temp = vRepo.findById(id);
		User usr = rRepo.findByUserName(username);
		if( usr != null && (usr.getRole() == "standard" || usr.getRole() == "admin")) {
			if(temp.isPresent()) {
				//TODO need to update notes separately
				Vendor result = temp.get();
				result.setEmail(mock.getEmail());
				result.setFname(mock.getFname());
				result.setLname(mock.getLname());
				result.setPhone(mock.getPhone());
				return vRepo.saveAndFlush(result);
			
			}
			
		}
		
		return null;
	}

	@Override
	public void destroy(String username, int id) {

		User usr = rRepo.findByUserName(username);
		
		if( usr != null && usr.getRole() == "admin") {
			 vRepo.deleteById(id);
		}
		
	}

}
