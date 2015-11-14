package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;

class DigitalClockController {
	
	CommandStore commandStore = new CommandStore();
	DigitalClockModel model;
	
	public DigitalClockController(DigitalClockModel model) {

		this.model = model;
		
	} // end of constructor
	
	private class MyButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			DigitalClockCmd command;
			
			if (e.getActionCommand().equals("Second")) {
				
				model.incrementSecond();
				
			} else if (e.getActionCommand().equals("Minute")) {
				
				model.incrementMin();
				
			} else if (e.getActionCommand().equals("Hour")) {
				
				model.incrementHour();
				
			} else if (e.getActionCommand().equals("Day") || e.getActionCommand().equals("Day of Week")) {
				
				command = new DTUpdateDayOfWeekCmd(model);
				commandStore.Execute(command);
				
			} else if (e.getActionCommand().equals("Month")) {
				
				command = new DTUpdateMonthCmd(model);
				commandStore.Execute(command);
				
			} else if (e.getActionCommand().equals("Year")) {
				
				command = new DTUpdateYearCmd(model);
				commandStore.Execute(command);
				
			} else if (e.getActionCommand().equals("Add Digital View")) {
				
				DigitalClockView digital = new DigitalClockDigitalView();
				model.addObserver(digital);
				digital.buildDigitalClockView();
				
			} else if (e.getActionCommand().equals("Add Dial View")) {
				
				DigitalClockView dial = new DigitalClock3DialsView();
				
				model.addObserver(dial);

				dial.buildDigitalClock3DialsView();
				
			} // end of if-else if-else
			
			model.notifyObservers();
			
		} // end of method actionPerformed
		
	} // end of class MyButtonListener
	
	private class CmdListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			if (e.getActionCommand().equals("Undo Command")) {
				
				commandStore.Undo();
				
			} else if (e.getActionCommand().equals("Redo Command")) {
				
				commandStore.Redo();
				
			} // end of if-else if
			
			model.notifyObservers();
			
		} // end of method actionPerformed
		
	} // end of class CmdListener
	
} // end of class DigitalClockController
