package src;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	
	DigitalClockModel model;
	CommandStore commandStore = new CommandStore();
	DigitalClockController controller;
	
	JButton dialButton;
	JButton digitalButton;
	JButton undo;
	JButton redo;
	
	JTextField secondsField;
	JTextField minutesField;
	JTextField hourField;
	JTextField yearField;
	
	JComboBox dayBox;
	JComboBox monthBox;
	
	public DigitalClockSettingsUI(DigitalClockModel model) {
		
		super();
		this.model = model;
		this.controller = new DigitalClockController(model);
		
		JPanel controllerPanel = new JPanel();
		controllerPanel.setLayout(new GridLayout(8, 2, 2, 2));
		
		dialButton = new JButton("Add Dial View");
		dialButton.addActionListener(new ButtonListener());
		controllerPanel.add(dialButton);
		
		digitalButton = new JButton("Add Digital View");
		digitalButton.addActionListener(new ButtonListener());
		controllerPanel.add(digitalButton);
		
		JLabel secondsLabel = new JLabel("Change Seconds");
		controllerPanel.add(secondsLabel);
		
		secondsField = new JTextField();
		secondsField.setEditable(true);
		secondsField.addKeyListener(new TextListener());
		controllerPanel.add(secondsField);
		
		JLabel minutesLabel = new JLabel("Change Minutes");
		controllerPanel.add(minutesLabel);
		
		minutesField = new JTextField();
		minutesField.setEditable(true);
		minutesField.addKeyListener(new TextListener());
		controllerPanel.add(minutesField);
		
		JLabel hourLabel = new JLabel("Change Hour");
		controllerPanel.add(hourLabel);
		
		hourField = new JTextField();
		hourField.setEditable(true);
		hourField.addKeyListener(new TextListener());
		controllerPanel.add(hourField);
		
		JLabel dayLabel = new JLabel("Change Day of Month");
		controllerPanel.add(dayLabel);
		
		String[] daysOfMonth = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", 
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		dayBox = new JComboBox(daysOfMonth);
		dayBox.addActionListener(new ButtonListener());
		controllerPanel.add(dayBox);
		
		JLabel monthLabel = new JLabel("Change Month");
		controllerPanel.add(monthLabel);
		
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December"};
		monthBox = new JComboBox(months);
		monthBox.addActionListener(new ButtonListener());
		controllerPanel.add(monthBox);
		
		JLabel yearLabel = new JLabel("Change Year");
		controllerPanel.add(yearLabel);
		
		yearField = new JTextField();
		yearField.setEditable(true);
		yearField.addKeyListener(new TextListener());
		controllerPanel.add(yearField);
		
		undo = new JButton("Undo Command");
		undo.addActionListener(new ButtonListener());
		controllerPanel.add(undo);
		
		redo = new JButton("Redo Command");
		redo.addActionListener(new ButtonListener());
		controllerPanel.add(redo);
		
		getContentPane().add(controllerPanel);
		
	} // end of constructor 
	
	
	private class TextListener implements KeyListener {
		
		public void keyPressed(KeyEvent enter) {
			
			if (enter.getKeyCode() == KeyEvent.VK_ENTER) {
			
				if (enter.getSource().equals(secondsField)) {

					String seconds = secondsField.getText();
					controller.updateSecond(seconds);
					//model.incrementSecond();

				} else if (enter.getSource().equals(minutesField)) {

					String minutes = minutesField.getText();
					controller.updateMinute(minutes);
					//model.incrementMin();

				} else if (enter.getSource().equals(hourField)) {

					String hour = hourField.getText();
					controller.updateHour(hour);
					//model.incrementHour();

				} else if (enter.getSource().equals(yearField)) {

					String year = yearField.getText();
					controller.updateYear(year);
					//command = new DTUpdateYearCmd(model);
					//commandStore.Execute(command);

				} // end of if-else if
			
			} // end of if-enter
			
		} // end of method keyPressed
		
		public void keyReleased(KeyEvent e) {}
		
		public void keyTyped(KeyEvent e) {}
		
	} // end of class TextListener
	
	
	private class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent click) {
			
			if (click.getSource().equals(digitalButton)) {
				
				controller.newDigitalView();
				
			} else if (click.getSource().equals(dialButton)) {
				
				controller.newDialView();
				
			} else if (click.getSource().equals(dayBox)) {
				
				JComboBox box = (JComboBox)click.getSource();
				String day = (String)box.getSelectedItem();
				
				controller.updateDay(day);
				
			} else if (click.getSource().equals(monthBox)) {
				
				JComboBox box = (JComboBox)click.getSource();
				String month = (String)box.getSelectedItem();
				
				controller.updateMonth(month);
				
			} else if (click.getSource().equals(undo)) {
				
				controller.undo();
				
			} else if (click.getSource().equals(redo)) {
				
				controller.redo();
				
			} // end of if-else if
			
		} // end of method actionPerformed
		
	} // end of class CmdListener
	
} // end of class DigitalClockSettingsUI
