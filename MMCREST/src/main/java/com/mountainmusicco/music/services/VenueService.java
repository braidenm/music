package com.mountainmusicco.music.services;

import java.util.Set;

import com.mountainmusicco.music.entities.Venue;

public interface VenueService {

	public Set<Venue> index(String username);
	public Venue show(String username, int id);
	public Venue create(String username, Venue mock);
	public Venue update(String username, Venue mock, int id);
	public void destroy(String username, int id);

}
