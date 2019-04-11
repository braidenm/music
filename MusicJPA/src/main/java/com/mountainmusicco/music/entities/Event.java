package com.mountainmusicco.music.entities;

import java.util.ArrayList;
import java.util.Date;
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
public class Event {

	
	// F E I L D S
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String category;
	@Column(name = "paid_in_full")
	private boolean paid;
	@Column(name = "amount_paid")
	private int amountPaid;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	@ManyToOne
	@JoinColumn(name = "package_id")
	private Package packageType;
	@OneToMany(mappedBy = "event")
	private List<Note> notes;
	@ManyToMany
	@JoinTable(name="employee_event",
	joinColumns=@JoinColumn(name="event_id"),
	inverseJoinColumns=@JoinColumn(name="employee"))
	private List<Employee> employees;
	
	// C O N S T R U C T O R S
	
	public Event(int id, String name, String category, boolean paid, int amountPaid, Date startDate, Date endDate,
			Package packageType) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.paid = paid;
		this.amountPaid = amountPaid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.packageType = packageType;
	}
	public Event() {
		super();
	}
	
	// G E T T E R S  /  S E T T E R S 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public int getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Package getPackageType() {
		return packageType;
	}
	public void setPackageType(Package packageType) {
		this.packageType = packageType;
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
		Event other = (Event) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	// T O S T R I N G
	
	@Override
	public String toString() {
		return name;
	}
	
	// M E T H O D S
	
	public Employee addEmployee(Employee employee) {
		if (employees == null) {
			employees = new ArrayList<Employee>();
		}
		if (!employees.contains(employee)) {
			employees.add(employee);
			employee.addEvent(this);
		}
		
		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		if (employees != null && employees.contains(employee)) {
			employees.remove(employee);
			employee.removeEvent(this);
		}
		
		return employee;
	}
	
	
	
	
}
