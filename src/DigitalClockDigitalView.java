package src;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class DigitalClockDigitalView  extends DigitalClockView {
	
	private JLabel time, date;
	private String timeString, dateString;
	
	public DigitalClockDigitalView() {
		
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.DARK_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10));
		
	} // end of constructor
	
	public void draw() {
		
		time = (JLabel) this.getComponent(0);
		time.setText(timeString + "\n");
		time.setFont(new Font("DejaVu Sans", Font.BOLD, 30));
		time.setForeground(Color.WHITE);
		time.setAlignmentX(CENTER_ALIGNMENT);
		
		date = (JLabel) this.getComponent(1);
		date.setText(dateString);
		date.setFont(new Font("DejaVu Sans", Font.PLAIN, 15));
		date.setForeground(Color.WHITE);
		date.setAlignmentX(CENTER_ALIGNMENT);
		
	} // end of method draw
	
	public void updateTime(int second, int minute, int hour) {
		
		this.timeString = hour + " : " + minute + " : " + second;
		
	} // end of method updateTime
	
	public void updateDate(String date) {
		
		this.dateString = date;
		
	} // end of method updateDate

} // end of class DigitalClockDigitalView
