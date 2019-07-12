package com.mountainmusicco.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findByUserName(String userName);

}
