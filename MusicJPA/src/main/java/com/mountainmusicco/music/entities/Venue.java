package com.mountainmusicco.music.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Venue {
	
	// F E I L D S
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Vendor owner;
	@OneToMany(mappedBy = "venue")
	private List<Note> notes;
	
	// C O N S T R U C T O R S 
	
	public Venue(int id, String name, Address address, Vendor owner) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.owner = owner;
	}
	public Venue() {
		super();
	}
	
	// G E T T E R S  /  S E T T E R S
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Vendor getOwner() {
		return owner;
	}
	public void setOwner(Vendor owner) {
		this.owner = owner;
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
		Venue other = (Venue) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	// T O S T R I N G 
	
	@Override
	public String toString() {
		return "Venue [name=" + name + "]";
	}
	
	

}
