package src;

import javax.swing.JLabel;

public class DigitalClockDigitalView  extends DigitalClockView {
	
	private JLabel label;
	//private int i = 0;
	
	public DigitalClockDigitalView() {
		
		super();
		
	} // end of constructor
	
	public void reset() {
		//hurr
	} // end of method reset
	
	public void draw() {
		
		label = (JLabel) this.getComponent(0);
		label.setText(model.toString());
		
	} // end of method draw

} // end of class DigitalClockDigitalView
