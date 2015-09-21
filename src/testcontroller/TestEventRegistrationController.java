package testcontroller;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test2.Event;
import test2.Participant;
import test2.PersistenceXStream;
import test2.Registration;
import test2.RegistrationManager;

public class TestEventRegistrationController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// DO THIS FROM SCREEN CAPS . ZIP
		PersistenceXStream.setFilename("src\\testcontroller\\TestControllerDATA.xml");
		PersistenceXStream.setAlias("event", Event.class);
		PersistenceXStream.setAlias("participant", Participant.class);
		PersistenceXStream.setAlias("registration", Registration.class);
		PersistenceXStream.setAlias("manager", RegistrationManager.class);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
		// clear all registrations
		RegistrationManager rm = RegistrationManager.getInstance();
		rm.delete();
		
	}
	
	@Test
	public void testCreateParticipant() {
	
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getParticipant().size());
		
		String name = "Oscar";
		
		EventRegistrationController erc = new EventRegistrationController();		
		String error = erc.createParticipant(name);
		
		// check error
		assertNull(error);
		
		// check model in memory
		checkResultParticipant(name, rm);
		RegistrationManager rm2 = (RegistrationManager) PersistenceXStream.loadFromXMLwithXStream();
		
		// check file contents
		checkResultParticipant(name, rm2);
				
	}
	
	@Test
	public void testCreateParticipantNull() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getParticipant().size());
		
		String name = null;
		
		EventRegistrationController erc = new EventRegistrationController();
		String error = erc.createParticipant(name);
		
		// check error 
		assertEquals("Participant name cannot be empty!", error);
		
		// check no change in memory
		assertEquals(0, rm.getParticipant().size());
		
	}

	@Test
	public void testCreateParticipantEmpty() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getParticipant().size());
		
		String name = "";
		
		EventRegistrationController erc = new EventRegistrationController();
		String error = erc.createParticipant(name);
		
		// check error 
		assertEquals("Participant name cannot be empty!", error);
		
		// check no change in memory
		assertEquals(0, rm.getParticipant().size());
		
	}
	
	@Test
	public void testCreateParticipantSpaces() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getParticipant().size());
		
		String name = " ";
		
		EventRegistrationController erc = new EventRegistrationController();
		String error = erc.createParticipant(name);
		
		// check error 
		assertEquals("Participant name cannot be empty!", error);
		
		// check no change in memory
		assertEquals(0, rm.getParticipant().size());
		
	}
	
	@Test
	public void testCreateEvent() {
		RegistrationManager rm = RegistrationManager.getInstance();
		assertEquals(0, rm.getEvents().size());
		
		String name = "Soccer Game";
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.OCTOBER, 16, 9, 00, 0);
		Date eventDate = new Date (c.getTimeInMillis());
		Time startTime = new Time (c.getTimeInMillis());
		c.set(2016, Calendar.OCTOBER, 16, 10, 30, 0);
		Time endTime = new Time (c.getTimeInMillis());
		
		EventRegistrationController erc = new EventRegistrationController();
		String error = erc.createEvent(name, eventDate, startTime, endTime);
		
		// check error
		assertNull(error);
		
		// check model in memory
		checkResultEvent(name, eventDate, startTime, endTime, rm);
		
		RegistrationManager rm2 = (RegistrationManager) PersistenceXStream.loadFromXMLwithXStream();
		
		// check file contents
		checkResultEvent(name, eventDate, startTime, endTime, rm2);
		
	}
	
	@Test
	public void testRegister(){
		
	}
	
	private void checkResultParticipant(String name, RegistrationManager rm2) {
		assertEquals(1, rm2.getParticipant().size());
		assertEquals(name, rm2.getParticipant(0).getName());
		assertEquals(0, rm2.getEvents().size());
		assertEquals(0, rm2.getRegistrations().size());
	}

	private void checkResultEvent(String name, Date eventDate, Time startTime, Time endTime, RegistrationManager rm2){
		assertEquals(0, rm2.getParticipant().size());
		assertEquals(1, rm2.getEvents().size());
		assertEquals(name, rm2.getEvent(0).getName());
		assertEquals(eventDate.toString(), rm2.getEvent(0).getEventDate().toString());
		assertEquals(startTime.toString(), rm2.getEvent(0).getStartTime().toString());
		assertEquals(endTime.toString(), rm2.getEvent(0).getEndTime().toString());
		assertEquals(0, rm2.getRegistrations().size());
	}
}
