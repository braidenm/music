package com.mountainmusicco.music.services;

import java.util.Set;

import com.mountainmusicco.music.entities.Vendor;

public interface VendorService {

	public Set<Vendor> index(String username);
	public Vendor show(String username, int id);
	public Vendor create(String username, Vendor mock);
	public Vendor update(String username, Vendor mock, int id);
	public void destroy(String username, int id);

}
