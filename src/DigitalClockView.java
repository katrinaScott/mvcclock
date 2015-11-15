package src;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** 
 * Digital Clock view base class
 *  
 *  This is an abstract class, rather than an interface. We need to do this so that 
 *  we can enforce every view in our software to be a JPanel 
 *	(this will help in our UI methods -- for example
 *  we can add any generic DigitalClockView to a UI because they are a Component).
 */

public abstract class DigitalClockView extends JPanel{
	
	/*//DD: Obviously, this is not a method that should be included in this
	//interface. It is only there to support this sample code. 
	public void draw() {};*/
	
	protected DigitalClockModel model;
	final int SEC = 0;
	final int MIN = 1;
	final int HR = 2;
	
	public void draw() {
		
		repaint();
		
	} // end of method draw
	
	public void addObservable(DigitalClockModel model) {
		
		this.model = model;
		
	} // end of method addObservable
	
	public void buildDigitalClockView() {
		
		JFrame digitalFrame = new JFrame("Digital");
		digitalFrame.add(this);
		this.add(new JLabel("time placeholder"));
		this.add(new JLabel("date placeholder"));
		
		digitalFrame.pack();
		digitalFrame.setSize(new Dimension(400, 100));
		digitalFrame.setLocation(new Point(20, 100));
		digitalFrame.setVisible(true);
		
	} // end of method buildDigitalClockView
	
	public void buildDigitalClock3DialsView() {
		
		JFrame dialFrame = new JFrame("3 Dial");
		dialFrame.getContentPane().add(this);
		dialFrame.pack();
		dialFrame.setSize(new Dimension(400, 300));
		dialFrame.setLocationRelativeTo(null);

		dialFrame.setVisible(true);
	} // end of method buildDigitalClock3DialsView

} // end of class DigitalClockView
