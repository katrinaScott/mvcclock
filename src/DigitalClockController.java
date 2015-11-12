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

class DigitalClockController extends JFrame {
	
	JButton secondButton;
	JButton minuteButton;
	JButton hourButton; 
	JButton dayButton;
	JButton dayOfWeekButton;
	JButton monthButton;
	JButton yearButton;
	
	JToggleButton modernToggle;
	JToggleButton dialToggle;
	
	JComboBox yearCombo;
	JComboBox daysOfWeekCombo;
	JComboBox dayCombo;
	JComboBox monthsCombo;
	
	CommandStore commandStore = new CommandStore();
	List<DigitalClockView> clockViews;
	DigitalClockModel model;
	
	public DigitalClockController(DigitalClockModel model) {
		
		super();
		this.model = model;
		secondButton = new JButton("Second");
		minuteButton = new JButton("Minute");
		hourButton = new JButton("Hour");
		dayButton = new JButton("Day");
		dayOfWeekButton = new JButton("Day of week");
		monthButton = new JButton("Month");
		yearButton = new JButton("Year");
		modernToggle = new JToggleButton("MODERN VIEW");
		dialToggle = new JToggleButton("DIAL VIEW");
		yearCombo = new JComboBox(model.years);
		daysOfWeekCombo = new JComboBox(model.generateDaysOfWeek());
		monthsCombo = new JComboBox(model.generateMonths());
		JButton redo = new JButton("redo");
		JButton undo = new JButton("undo");
		
		JPanel p = new JPanel();
		
		secondButton.addActionListener(new MyButtonListener());
		minuteButton.addActionListener(new MyButtonListener());
		hourButton.addActionListener(new MyButtonListener());
		dayButton.addActionListener(new MyButtonListener());
		dayOfWeekButton.addActionListener(new MyButtonListener());
		monthButton.addActionListener(new MyButtonListener());
		yearButton.addActionListener(new MyButtonListener());
		modernToggle.setSelected(true);
		modernToggle.addItemListener(new MyItemListener("modern"));
		dialToggle.setSelected(true);
		dialToggle.addItemListener(new MyItemListener("dial"));
		undo.addActionListener(new UndoListener());
		redo.addActionListener(new RedoListener());
		
		p.add(secondButton);
		p.add(hourButton);
		p.add(minuteButton);
		p.add(dayButton);
		p.add(dayOfWeekButton);
		p.add(monthButton);
		p.add(yearButton);
		p.add(dialToggle);
		p.add(modernToggle);
//		p.add(yearCombo);
//		p.add(daysOfWeekCombo);
//		p.add(monthsCombo);
		p.add(redo);
		p.add(undo);
		this.setContentPane(p);
		
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
				
			} // end of if-else if-else
			
			model.notifyObservers();
			
		} // end of method actionPerformed
		
	} // end of class MyButtonListener
	
	private class UndoListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			commandStore.Undo();
			model.notifyObservers();
			
		} // end of method actionPerformed
		
	} // end of class undoListener
	
	private class RedoListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			 
			commandStore.Redo();
			model.notifyObservers();
			
		} // end of method actionPerformed
		
	} // end of class RedoListener
	
	private class MyItemListener implements ItemListener {
		
		private String buttonName;
		
		public MyItemListener(String buttonName) {
			
			this.buttonName = buttonName;
			
		} // end of constructor
		
		private void toggleOffView() {
			
			if (buttonName.equals("digital")) {
				
				 ((JFrame) SwingUtilities.getWindowAncestor(model.getObserver(1))).setVisible(false);
				 
			} else if (buttonName.equals("dial")) {
				
				 ((JFrame) SwingUtilities.getWindowAncestor(model.getObserver(0))).setVisible(false);
				
			} // end of if-else if
			
		} // end of method toggleOffView
		
		private void toggleOnView() {
			
			if (buttonName.equals("digital")) {
				
				 ((JFrame) SwingUtilities.getWindowAncestor(model.getObserver(1))).setVisible(false);
				 
			} else if (buttonName.equals("dial")) {
				
				 ((JFrame) SwingUtilities.getWindowAncestor(model.getObserver(0))).setVisible(false);
				
			} // end of if-else if
			
		} // end of method toggleOnView
		
		public void itemStateChanged(ItemEvent e) {
			
			if (e.getStateChange() == ItemEvent.SELECTED) {
				
				toggleOnView();
				
			} else if (e.getStateChange() == ItemEvent.DESELECTED) {
				
				toggleOffView();
				
			} // end of if-else if
			
		} // end of method itemStateChanged
		
	} // end of class MyItemListener
	
} // end of class DigitalClockController
