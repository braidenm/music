package com.mountainmusicco.music.services;

import java.util.Set;

import com.mountainmusicco.music.entities.Client;

public interface ClientService {

	public Set<Client> index(String username);
	public Client show(String username, Integer id);
	public Client create(String username, Client mock);
	public Client update(String username, Client mock, Integer id);
	public void destroy(String username, Integer id);

}
