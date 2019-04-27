package com.mountainmusicco.music.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mountainmusicco.music.entities.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
