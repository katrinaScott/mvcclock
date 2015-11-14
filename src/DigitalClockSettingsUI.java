package src;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * Options UI
 * This is the class that lets a user change the settings of the 
 * clock. Changes in settings will create commands. Commands will 
 * modify the model directly. The controller will then be alerted 
 * that a change occured (just as if one second passed), and update
 * all registered views accordingly.
 */
class DigitalClockSettingsUI extends JFrame {
	
	CommandStore commandStore = new CommandStore();
	DigitalClockModel model;
	
	public DigitalClockSettingsUI(DigitalClockModel model) {
		
		super();
		this.model = model;
		
		JPanel controllerPanel = new JPanel();
		controllerPanel.setLayout(new GridLayout(8, 2, 2, 2));
		
		JButton dialButton = new JButton("Add Dial View");
		// add listener
		controllerPanel.add(dialButton);
		
		JButton digitalButton = new JButton("Add Digital View");
		// add listener
		controllerPanel.add(digitalButton);
		
		JLabel secondsLabel = new JLabel("Change Seconds");
		controllerPanel.add(secondsLabel);
		
		JTextArea secondsArea = new JTextArea();
		secondsArea.setEditable(true);
		// add listener
		controllerPanel.add(secondsArea);
		
		JLabel minutesLabel = new JLabel("Change Minutes");
		controllerPanel.add(minutesLabel);
		
		JTextArea minutesArea = new JTextArea();
		minutesArea.setEditable(true);
		// add listener
		controllerPanel.add(minutesArea);
		
		JLabel hourLabel = new JLabel("Change Hour");
		controllerPanel.add(hourLabel);
		
		JTextArea hourArea = new JTextArea();
		hourArea.setEditable(true);
		// add listener
		controllerPanel.add(hourArea);
		
		JLabel dayLabel = new JLabel("Change Day of Month");
		controllerPanel.add(dayLabel);
		
		String[] daysOfMonth = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", 
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		JComboBox dayBox = new JComboBox(daysOfMonth);
		// add action listener
		controllerPanel.add(dayBox);
		
		JLabel monthLabel = new JLabel("Change Month");
		controllerPanel.add(monthLabel);
		
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December"};
		JComboBox monthBox = new JComboBox(months);
		// add action listener
		controllerPanel.add(monthBox);
		
		JLabel yearLabel = new JLabel("Change Year");
		controllerPanel.add(yearLabel);
		
		JTextArea yearArea = new JTextArea();
		yearArea.setEditable(true);
		// add listener
		controllerPanel.add(yearArea);
		
		JButton undo = new JButton("Undo Command");
		// add listener
		controllerPanel.add(undo);
		
		JButton redo = new JButton("Redo Command");
		// add listener
		controllerPanel.add(redo);
		
		getContentPane().add(controllerPanel);
		
	} // end of constructor 
	
} // end of class DigitalClockSettingsUI
