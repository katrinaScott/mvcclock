package src;

import java.awt.Dimension;
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
	
	protected DigitalClockController controller;
	
	public void draw() {
		
		repaint();
		
	} // end of method draw
	
	public void addObservable(DigitalClockController controller) {
		
		this.controller = controller;
		
	} // end of method addObservable
	
	public void buildDigitalClockView() {
		
		JFrame digitalFrame = new JFrame("Digital");
		digitalFrame.add(this);
		this.add(new JLabel());
		this.add(new JLabel());
		
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
	
	public void updateTime(int second, int minute, int hour) {
		
		System.out.println("wrong update");
		
	} // end of method updateTime

} // end of class DigitalClockView
