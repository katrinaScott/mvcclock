package src;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

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
	
	JComboBox<String> dayBox;
	JComboBox<String> monthBox;
	
	public DigitalClockSettingsUI(DigitalClockModel model, DigitalClockController controller) {
		
		super();
		this.setTitle("Settings");
		this.model = model;
		this.controller = controller;
		
		JPanel controllerPanel = new JPanel();
		controllerPanel.setLayout(new GridLayout(8, 2, 5, 5));
		controllerPanel.setBackground(Color.DARK_GRAY);
		controllerPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
		
		dialButton = new JButton("ADD DIAL VIEW");
		dialButton.addActionListener(new ButtonListener());
		dialButton.setFont(new Font("DejaVu Sans", Font.PLAIN, 15));
		dialButton.setBackground(Color.GRAY);
		dialButton.setForeground(Color.DARK_GRAY);
		controllerPanel.add(dialButton);
		
		digitalButton = new JButton("ADD DIGITAL VIEW");
		digitalButton.addActionListener(new ButtonListener());
		digitalButton.setFont(new Font("DejaVu Sans", Font.PLAIN, 15));
		digitalButton.setBackground(Color.GRAY);
		digitalButton.setForeground(Color.DARK_GRAY);
		controllerPanel.add(digitalButton);
		
		JLabel secondsLabel = new JLabel("Change Seconds");
		secondsLabel.setForeground(Color.WHITE);
		secondsLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 15));
		controllerPanel.add(secondsLabel);
		
		secondsField = new JTextField();
		secondsField.setEditable(true);
		secondsField.addKeyListener(new TextListener());
		secondsField.setBackground(Color.GRAY);
		secondsField.setForeground(Color.WHITE);
		secondsField.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		controllerPanel.add(secondsField);
		
		JLabel minutesLabel = new JLabel("Change Minutes");
		minutesLabel.setForeground(Color.WHITE);
		minutesLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 15));
		controllerPanel.add(minutesLabel);
		
		minutesField = new JTextField();
		minutesField.setEditable(true);
		minutesField.addKeyListener(new TextListener());
		minutesField.setBackground(Color.GRAY);
		minutesField.setForeground(Color.WHITE);
		minutesField.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		controllerPanel.add(minutesField);
		
		JLabel hourLabel = new JLabel("Change Hour");
		hourLabel.setForeground(Color.WHITE);
		hourLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 15));
		controllerPanel.add(hourLabel);
		
		hourField = new JTextField();
		hourField.setEditable(true);
		hourField.addKeyListener(new TextListener());
		hourField.setBackground(Color.GRAY);
		hourField.setForeground(Color.WHITE);
		hourField.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		controllerPanel.add(hourField);
		
		JLabel dayLabel = new JLabel("Change Day of Month");
		dayLabel.setForeground(Color.WHITE);
		dayLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 15));
		controllerPanel.add(dayLabel);
		
		String[] daysOfMonth = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", 
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		dayBox = new JComboBox<String>(daysOfMonth);
		dayBox.addActionListener(new ButtonListener());
		dayBox.setBackground(Color.GRAY);
		dayBox.setForeground(Color.WHITE);
		dayBox.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		controllerPanel.add(dayBox);
		
		JLabel monthLabel = new JLabel("Change Month");
		monthLabel.setForeground(Color.WHITE);
		monthLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 15));
		controllerPanel.add(monthLabel);
		
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December"};
		monthBox = new JComboBox<String>(months);
		monthBox.addActionListener(new ButtonListener());
		monthBox.setBackground(Color.GRAY);
		monthBox.setForeground(Color.WHITE);
		monthBox.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		controllerPanel.add(monthBox);
		
		JLabel yearLabel = new JLabel("Change Year");
		yearLabel.setForeground(Color.WHITE);
		yearLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 15));
		controllerPanel.add(yearLabel);
		
		yearField = new JTextField();
		yearField.setEditable(true);
		yearField.addKeyListener(new TextListener());
		yearField.setBackground(Color.GRAY);
		yearField.setForeground(Color.WHITE);
		yearField.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
		controllerPanel.add(yearField);
		
		undo = new JButton("Undo Command");
		undo.addActionListener(new ButtonListener());
		undo.setBackground(Color.GRAY);
		undo.setForeground(Color.DARK_GRAY);
		undo.setFont(new Font("DejaVu Sans", Font.PLAIN, 15));
		controllerPanel.add(undo);
		
		redo = new JButton("Redo Command");
		redo.addActionListener(new ButtonListener());
		redo.setBackground(Color.GRAY);
		redo.setForeground(Color.DARK_GRAY);
		redo.setFont(new Font("DejaVu Sans", Font.PLAIN, 15));
		controllerPanel.add(redo);
		
		getContentPane().add(controllerPanel);
		
	} // end of constructor 
	
	
	private class TextListener implements KeyListener {
		
		public void keyPressed(KeyEvent enter) {
			
			if (enter.getKeyCode() == KeyEvent.VK_ENTER) {
			
				if (enter.getSource().equals(secondsField)) {

					String seconds = secondsField.getText();
					controller.updateSecond(seconds);

				} else if (enter.getSource().equals(minutesField)) {

					String minutes = minutesField.getText();
					controller.updateMinute(minutes);

				} else if (enter.getSource().equals(hourField)) {

					String hour = hourField.getText();
					controller.updateHour(hour);

				} else if (enter.getSource().equals(yearField)) {

					String year = yearField.getText();
					controller.updateYear(year);

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
				
				JComboBox<String> box = (JComboBox<String>)click.getSource();
				String day = (String)box.getSelectedItem();
				
				controller.updateDay(day);
				
			} else if (click.getSource().equals(monthBox)) {
				
				JComboBox<String> box = (JComboBox<String>)click.getSource();
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
