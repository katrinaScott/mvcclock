package src;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class DigitalClock extends JFrame {

	static DigitalClockModel model;
	
	public static void main(String argv[]) {
		
		model = new DigitalClockModel();

		DigitalClockController controller = new DigitalClockController(model);
		model.setController(controller);
		buildControllerUI(model, controller);

		model.start();
		
	} // end of main method
	
	private static void buildControllerUI(DigitalClockModel model, DigitalClockController controller) {
		
		JFrame controllerFrame = new DigitalClockSettingsUI(model, controller);
		controllerFrame.addWindowListener(windowAdapter);
		controllerFrame.setSize(new Dimension(400, 450));
		controllerFrame.setLocation(new Point(600, 100));
		controllerFrame.setVisible(true);
		
	} // end of method buildControllerView
	
	static WindowAdapter windowAdapter = new WindowAdapter() {
		
		public void windowClosing(WindowEvent e) {
			
			model.stop();
			System.exit(0);
			
		} // end of method windowClosing
		
	}; // end of class WindowAdapter
	
} // end of class DigitalClock
