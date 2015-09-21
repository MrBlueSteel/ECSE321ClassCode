package testview;

public class EventRegistration {

	public static void main (String [] args){
		
		// load model 
		PersistenceEventRegistration.loadEventRegistrationModel();
		
		// user interface > page
		java.awt.EventQueue.invokeLater(new Runnable () {
			public void run() {
				new EventRegistrationPage().setVisible(true);
			}
		});
	}
}
