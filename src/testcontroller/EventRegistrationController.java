package testcontroller;

import java.sql.Date;
import java.sql.Time;

import test2.*;

public class EventRegistrationController {
	public EventRegistrationController () {
		
	}
	
	public String createParticipant (String name){
		
		if (name == null || name.trim().length() == 0)
			return "Participant name cannot be empty!";
		
		Participant p = new Participant(name);
		RegistrationManager rm = RegistrationManager.getInstance();		// persistenceXStream work needed, probably in ump file
		rm.addParticipant(p);
		
		PersistenceXStream.saveToXMLwithXStream(rm);
		
		return null;
	}

	public String createEvent (String name, Date date, Time startTime, Time endTime){
		return null;
	}
	
	public String register (Participant participant, Event event){
		return null;
	}
}
