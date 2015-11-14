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
	DigitalClockCmd command;
	DigitalClockModel model;
	
	public DigitalClockController(DigitalClockModel model) {

		this.model = model;
		
	} // end of constructor
	
	public void newDigitalView() {
		
		DigitalClockView digital = new DigitalClockDigitalView();
		model.addObserver(digital);
		digital.buildDigitalClockView();
		
		model.notifyObservers();
		
	} // end of method newDigitalView
	
	public void newDialView(){
		
		DigitalClockView dial = new DigitalClock3DialsView();
		model.addObserver(dial);
		dial.buildDigitalClock3DialsView();
		
		model.notifyObservers();
		
	} // end of method newDialView
	
	public void updateSecond(String second) {
		
		command = new DTUpdateSecondCmd(model, second);
		commandStore.Execute(command);
		
		model.notifyObservers();
		
	} // end of method updateSecond
	
	public void updateMinute(String minutes) {
		
		command = new DTUpdateMinuteCmd(model, minutes);
		commandStore.Execute(command);
		
		model.notifyObservers();
		
	} // end of method updateMinute
	
	public void updateHour(String hour) {
		
		command = new DTUpdateHourCmd(model, hour);
		commandStore.Execute(command);
		
		model.notifyObservers();
		
	} // end of method updateHour
	
	public void updateDay(String day) {
		
		command = new DTUpdateDayCmd(model, day);
		commandStore.Execute(command);
		
		model.notifyObservers();
		
	} // end of method updateDay
	
	public void updateMonth(String month) {
		
		command = new DTUpdateMonthCmd(model, month);
		commandStore.Execute(command);
		
		model.notifyObservers();
		
	} // end of method updateMonth
	
	public void updateYear(String year) {
		
		command = new DTUpdateYearCmd(model, year);
		commandStore.Execute(command);
		
		model.notifyObservers();
		
	} // end of method updateYear
	
	public void undo() {
		
		commandStore.Undo();
		
		model.notifyObservers();
		
	} // end of method undo
	
	public void redo() {
		
		commandStore.Redo();
		
		model.notifyObservers();
		
	} // end of method redo
	
} // end of class DigitalClockController
