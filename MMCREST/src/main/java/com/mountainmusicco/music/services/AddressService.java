package com.mountainmusicco.music.services;

import java.util.Set;

import com.mountainmusicco.music.entities.Address;

public interface AddressService {

	public Set<Address> index(String username);
	public Address show(String username, Integer id);
	public Address create(String username, Address mock);
	public Address update(String username, Address mock, Integer id);
	public void destroy(String username, Integer id);

}
