package com.mountainmusicco.music.entities;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
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
		List<EventNote> notes = e.getNotes();
		assertTrue(e != null);
		assertTrue(e.getEmployees().size() > 1);
		assertEquals(1200, e.getPackageType().getPrice());
		assertEquals("testing event", notes.get(0).getNote());
		assertTrue(e.getVendors().size() > 1);
		assertTrue(e.getVenues().size() == 1);
		
//		List<List<Integer>> nums = new ArrayList<>();
//		List<Integer> a = new ArrayList<>();
//		List<Integer> b = new ArrayList<>();
//		List<Integer> c = new ArrayList<>();
//		List<Integer> d = new ArrayList<>();
//		List<Integer> e = new ArrayList<>();
//		a.add(1);
//		a.add(5);
//		b.add(2);
//		b.add(6);
//		c.add(3);
//		c.add(8);
//		d.add(4);
//		d.add(9);
//		e.add(5);
//		e.add(3);
//		
//		nums.add(c);
//		nums.add(a);
//		nums.add(d);
//		nums.add(b);
//		nums.add(e);
//		
//		cacheContents(nums);
		
		
	}
//	public static List<Integer> cacheContents(List<List<Integer>> callLogs) {
//	    // Write your code here
//	        List<Integer> result = new ArrayList<>();
//	        //list of the ids and their priority
//	        List<List<Integer>> priority = new ArrayList<>();
//
//	        callLogs = sortLists(callLogs);
//
//	        //populating the priority list
//	        for (List<Integer> log: callLogs){
//	            List<Integer> temp = new ArrayList<>();
//	            //id number
//	            temp.add(log.get(1));
//	            //number of priority
//	            temp.add(0);
//	            //cache status 0 for no 1 for yes
//	            temp.add(0);
//	            priority.add(temp);
//	        }
//
//	        //main logic for sorting out what will be in the cache at the end of the log
//	        for(int i = 0; i < callLogs.size(); i++){
//	            List<Integer> log = callLogs.get(i);
//	            for(int j = 0; j < priority.size(); j++){
//	                if(log.get(1) == priority.get(j).get(0)){
//	                    priority.get(j).set(1, priority.get(j).get(1) + 2);
//	                    if(priority.get(j).get(1) > 5){
//	                        priority.get(j).set(2, 1);
//	                    }
//	                }
//	                for(int k = 0; k < priority.size(); k++) {
//	                	if(j == k){
//	                		continue;
//	                	}
//	                	if(priority.get(k).get(1) == 0) {
//	                		continue;
//	                	}
//	                    priority.get(k).set(1, priority.get(k).get(1) - 1);
//	                    if (priority.get(k).get(1) <= 3 && priority.get(k).get(2) == 1) {
//	                        priority.get(k).set(2, 0);
//	                    }
//	                }
//	            }
//	        }
//	        for(List<Integer> status: priority){
//	            if(status.get(2) == 1){
//	                result.add(status.get(0));
//	            }
//	        }
//	        return result;
//	    }
//	    private static List<List<Integer>> sortLists(List<List<Integer>> nums){
//	        int size = nums.size();
//	        for (int i = 0; i < size - 1; i++){
//	            for (int j = 0; j < size - i - 1; j++){
//	                if (nums.get(j).get(0) > nums.get(j + 1).get(0)) {
//	                    List<Integer> temp = nums.get(j);
//	                    nums.set(j, nums.get(j + 1));
//	                    nums.set(j +1, temp);
//	                }
//	            }
//	        }
//	        return nums;
//	    }

	
	


}
