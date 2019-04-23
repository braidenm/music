package com.mountainmusicco.music.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Client {
	
	// F E I L D S
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="first_name")
	private String fname;
	@Column(name="last_name")
	private String lname;
	@Column(name="fiancee_fname")
	private String fianceeFname;
	@Column(name="fiancee_lname")
	private String fianceeLname;
	private String phone;
	private String email;
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;
	@OneToMany(mappedBy = "client")
	private List<Note> notes;
	@ManyToMany
	@JoinTable(name="client_event",
	joinColumns=@JoinColumn(name="client_id"),
	inverseJoinColumns=@JoinColumn(name="event_id"))
	private List<Event> events;
	
	// C O N S T R U C T O R S
	
	public Client(int id, String fname, String lname, String fianceeFname, String fianceeLname, String phone,
			String email, Address address) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.fianceeFname = fianceeFname;
		this.fianceeLname = fianceeLname;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}
	public Client() {
		super();
	}
	
	// G E T T E R S  /  S E T T E R S
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFianceeFname() {
		return fianceeFname;
	}
	public void setFianceeFname(String fianceeFname) {
		this.fianceeFname = fianceeFname;
	}
	public String getFianceeLname() {
		return fianceeLname;
	}
	public void setFianceeLname(String fianceeLname) {
		this.fianceeLname = fianceeLname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
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
		Client other = (Client) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	// T O S T R I N G
	
	@Override
	public String toString() {
		return fname + " " + lname;
	}
	
	// M E T H O D S
	
		public Event addEvent(Event event) {
			if (events == null) {
				events = new ArrayList<Event>();
			}
			if (!events.contains(event)) {
				events.add(event);
				event.addClient(this);
			}
			
			return event;
		}

		public Event removeEvent(Event event) {
			if (events != null && events.contains(event)) {
				events.remove(event);
				event.removeClient(this);
			}
			
			return event;
		}
	
	

}
