package testview;

import java.util.Iterator;

import test2.Event;
import test2.Participant;
import test2.PersistenceXStream;
import test2.Registration;
import test2.RegistrationManager;

public class PersistenceEventRegistration {
	
	private static void initializeXStream() {
		PersistenceXStream.setFilename("src\\testview\\eventRegistration.xml");
		PersistenceXStream.setAlias("event", Event.class);
		PersistenceXStream.setAlias("participant", Participant.class);
		PersistenceXStream.setAlias("registration", Registration.class);
		PersistenceXStream.setAlias("manager", RegistrationManager.class);
	}
	
	public static void loadEventRegistrationModel() {
		RegistrationManager rm = RegistrationManager.getInstance();
		PersistenceEventRegistration.initializeXStream();
		
		RegistrationManager rm2 = (RegistrationManager) PersistenceXStream.loadFromXMLwithXStream();
		
		if (rm2 != null){
			// we create a second registration manager even though it is singleton
			// we copy the loaded model into the singleton instance of the registration manager
			
			Iterator <Participant> pIt = rm2.getParticipant().iterator();
			while (pIt.hasNext())
				rm.addParticipant(pIt.next());
			
			Iterator <Event> eIt = rm2.getEvents().iterator();
			while (eIt.hasNext())
				rm.addEvent(eIt.next());
			
			Iterator <Registration> rIt = rm2.getRegistrations().iterator();
			while (rIt.hasNext())
				rm.addRegistration(rIt.next());
			
			
		}
	}
}
