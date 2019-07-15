package com.mountainmusicco.music.services;

import java.util.Set;

import com.mountainmusicco.music.entities.Event;

public interface EventService {

	public Set<Event> index(String username);
	public Event show(String username, int id);
	public Event create(String username, Event mock);
	public Event update(String username, Event mock, int id);
	public void destroy(String username, int id);

}
