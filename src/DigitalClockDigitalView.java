package src;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class DigitalClockDigitalView  extends DigitalClockView {
	
	private JLabel time, date;
	
	public DigitalClockDigitalView() {
		
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
	} // end of constructor
	
	public void draw() {
		
		time = (JLabel) this.getComponent(0);
		time.setText(model.timeToString() + "\n");
		time.setFont(new Font("DejaVu Sans", Font.BOLD, 30));
		time.setAlignmentX(CENTER_ALIGNMENT);
		
		date = (JLabel) this.getComponent(1);
		date.setText(model.dateToString());
		date.setFont(new Font("DejaVu Sans", Font.PLAIN, 15));
		date.setAlignmentX(CENTER_ALIGNMENT);
		
	} // end of method draw

} // end of class DigitalClockDigitalView
