package com.mountainmusicco.music.entities;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Event e;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("Music");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@DisplayName("test the event entity to see if all of the objects come back from the DB")
	void test() {
		
		e = em.find(Event.class, 1);
		List<Note> notes = e.getNotes();
		assertTrue(e != null);
		assertTrue(e.getEmployees().size() > 1);
		assertEquals(1200, e.getPackageType().getPrice());
		assertEquals("testing event", notes.get(0).getNote());
		assertTrue(e.getVendors().size() > 1);
		assertTrue(e.getVenues().size() == 1);
		
	}

}
