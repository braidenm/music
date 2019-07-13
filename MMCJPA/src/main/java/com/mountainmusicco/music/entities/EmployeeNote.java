package com.mountainmusicco.music.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="employee_note")
public class EmployeeNote {

	
	// F E I L D S
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String note;
	@CreationTimestamp
	private Date created;
	@UpdateTimestamp
	private Date updated;
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	private boolean active;
	
	// C O N S T R U C T O R S
	
	public EmployeeNote(int id, String title, String note, Date created, Date updated, Employee employee, boolean active) {
		super();
		this.id = id;
		this.title = title;
		this.note = note;
		this.created = created;
		this.updated = updated;
		this.employee = employee;
		
		this.active = active;
	}
	public EmployeeNote() {
		super();
	}
	
	// G E T T E R S  /  S E T T E R S
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee venue) {
		this.employee = venue;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	// H A S H C O D E  /  E Q U A L S
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeNote other = (EmployeeNote) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	// T O S T R I N G
	
	@Override
	public String toString() {
		return title;
	}
	
	
	
}

