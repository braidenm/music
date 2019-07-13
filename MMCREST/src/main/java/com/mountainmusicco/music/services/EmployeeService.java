package com.mountainmusicco.music.services;

import java.util.Set;

import com.mountainmusicco.music.entities.Employee;

public interface EmployeeService {

	public Set<Employee> index(String username);
	public Employee show(String username, int id);
	public Employee create(String username, Employee mock);
	public Employee update(String username, Employee mock, int id);
	public void destroy(String username, int id);

}
