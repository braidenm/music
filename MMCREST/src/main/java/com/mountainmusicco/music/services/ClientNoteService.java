package com.mountainmusicco.music.services;

import java.util.Set;

import com.mountainmusicco.music.entities.ClientNote;

public interface ClientNoteService {

	public Set<ClientNote> index(String username);
	public ClientNote show(String username, Integer id);
	public ClientNote create(String username, ClientNote mock);
	public ClientNote update(String username, ClientNote mock, Integer id);
	public void destroy(String username, Integer id);

}
