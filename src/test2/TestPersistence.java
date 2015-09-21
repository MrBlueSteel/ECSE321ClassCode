package test2;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPersistence {

	@Before
	public void setUp() throws Exception {
		//DO THIS WITH SCREENS FROM CLASS .ZIP
		RegistrationManager rm = RegistrationManager.getInstance();
		
		// participants
		Participant pa1 = new Participant("Martin");
		Participant pa2 = new Participant("Jennifer");
		
		// events
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.SEPTEMBER, 15, 8, 30, 0);
		Date eventDate = new Date (c.getTimeInMillis());
		Time startTime = new Time (c.getTimeInMillis());
		
		c.set(2015, Calendar.SEPTEMBER, 15, 10, 0, 0);
		Time endTime = new Time (c.getTimeInMillis());
		Event ev = new Event (startTime, endTime, "Concert", eventDate);
		
		// participants > events
		Registration re1 = new Registration (pa1, ev);
		Registration re2 = new Registration (pa2, ev);
		
		// manage registration
		rm.addRegistration(re1);
		rm.addRegistration(re2);
		rm.addEvent(ev);
		rm.addParticipant(pa1);
		rm.addParticipant(pa2);
	}

	@After
	public void tearDown() throws Exception {
		
		// clear registrations
		RegistrationManager rm = RegistrationManager.getInstance();
		rm.delete();
		
	}

	@Test
	public void test() {
		
		// save model 
		RegistrationManager rm = RegistrationManager.getInstance();
		PersistenceXStream.setFilename("src\\test2\\TestPersistenceDATA.xml");
		PersistenceXStream.setAlias("event", Event.class);
		PersistenceXStream.setAlias("participant", Participant.class);
		PersistenceXStream.setAlias("registration", Registration.class);
		PersistenceXStream.setAlias("manager", RegistrationManager.class);
		
		if(!PersistenceXStream.saveToXMLwithXStream(rm))
			fail("Could not SAVE!");
		
		// clear model
		rm.delete();
		assertEquals(0, rm.getParticipant().size());
		assertEquals(0, rm.getEvents().size());
		assertEquals(0, rm.getRegistrations().size());

		// load model
		rm = (RegistrationManager) PersistenceXStream.loadFromXMLwithXStream();
		if (rm == null)
			fail("Could not LOAD!");
		
		// check participants
		assertEquals(2, rm.getParticipant().size());
		assertEquals("Martin", rm.getParticipant(0).getName());
		assertEquals("Jennifer", rm.getParticipant(1).getName());

		// check event
		assertEquals(1, rm.getEvents().size());
		assertEquals("Concert", rm.getEvent(0).getName());
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.SEPTEMBER, 15, 8, 30, 0);
		Date eventDate = new Date (c.getTimeInMillis());
		Time startTime = new Time (c.getTimeInMillis());
		c.set(2015, Calendar.SEPTEMBER, 15, 10, 0, 0);
		Time endTime = new Time (c.getTimeInMillis());
		
		assertEquals(eventDate.toString(), rm.getEvent(0).getEventDate().toString());
		assertEquals(startTime.toString(), rm.getEvent(0).getStartTime().toString());
		assertEquals(endTime.toString(), rm.getEvent(0).getEndTime().toString());

		// check registration
		assertEquals(2, rm.getRegistrations().size());
		assertEquals(rm.getEvent(0), rm.getRegistration(0).getEvent());
		assertEquals(rm.getParticipant(0), rm.getRegistration(0).getParticipant());
		assertEquals(rm.getEvent(0), rm.getRegistration(1).getEvent());
		assertEquals(rm.getParticipant(1), rm.getRegistration(1).getParticipant());
	}

}
