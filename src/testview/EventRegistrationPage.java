package testview;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import testcontroller.EventRegistrationController;

public class EventRegistrationPage extends JFrame {

	private static final long serialVersionUID = 6936610274784970119L;
	
	// UI elements 
	private JLabel errorMessage;
	
	private JLabel participantNameLabel;
	private JTextField participantNameTextField;
	private JButton addParticipantButton;
	
	// data elements 
	private String error = null;
	
	// new form registration page
	public EventRegistrationPage() {
		initComponents();
		refreshData();
	}
	
	// constructor for form
	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);		
		
		// elements for participant
		participantNameTextField = new JTextField();
		participantNameLabel = new JLabel();
		addParticipantButton = new JButton();
		
		// global settings & listeners 
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Event Registration");
		
		participantNameLabel.setText("Name: ");
		addParticipantButton.setText("Add Participant");
		addParticipantButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed (java.awt.event.ActionEvent evt) {
				addParticipantButtonActionPerformed(evt);
			}
		});
		
		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(
				layout.createSequentialGroup()
				.addComponent(participantNameLabel)
				.addGroup(layout.createParallelGroup()
						.addComponent(participantNameTextField, 200, 200, 400)
						.addComponent(addParticipantButton))
						 )
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addParticipantButton, participantNameTextField});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(participantNameLabel)
						.addComponent(participantNameTextField))
				.addComponent(addParticipantButton)
				);
		
		pack();
	}
	
	private void addParticipantButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// call controller
		EventRegistrationController erc = new EventRegistrationController();
		
		error = erc.createParticipant(participantNameTextField.getText());
		//update visuals
		refreshData();		
	}
	
	private void refreshData() {
		// error 
		errorMessage.setText(error);
		
		// participant refreshing data
		participantNameTextField.setText("");
		
		// we need this for the window size change depending on error message
		pack();
	}
}
