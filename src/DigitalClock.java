package src;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * This is our **main** class for a DigitalClock
 * hcl05001 Homework4
 */

public class DigitalClock extends JFrame {
	
	CommandStore cmdStore = new CommandStore();
	static DigitalClockModel model;
	
	public static void main(String argv[]) {
	        
			/*//DD *note -- this really doesn't follow the MVC pattern yet.
			//Here, we are registering a view with a model. We should instead register
			//a controller with the model, and as the user opens views we register
			//views with the controller. 
	        DigitalClockView dtView = new DigitalClock3DialsView();
	        final DigitalClockModel dtModel = new DigitalClockModel(dtView);
	        dtModel.start();
	        
	        JFrame f = new JFrame("Digitial Clock: Design Patterns implementation");
	        f.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {dtModel.stop(); System.exit(0);}
	            public void windowDeiconified(WindowEvent e) { dtModel.start(); }
	            public void windowIconified(WindowEvent e) { dtModel.stop(); }
	        });
	        f.getContentPane().add("Center", dtView);
	        f.pack();
	        f.setSize(new Dimension(400,300));
	        f.show();*/
		
		DigitalClockView dialView = new DigitalClock3DialsView();
		DigitalClockView digitalView = new DigitalClockDigitalView();
		
		model = new DigitalClockModel();
		//model = new DigitalClockModel(digitalView);
		
		model.addObserver(dialView);
		model.addObserver(digitalView);
		
		buildControllerView(model);
		digitalView.buildDigitalClockView();
		dialView.buildDigitalClock3DialsView();

		model.start();
		
	} // end of main method
	
	private static void buildControllerView(DigitalClockModel model) {
		
		JFrame controllerFrame = new DigitalClockSettingsUI(model);
		controllerFrame.addWindowListener(windowAdapter);
		controllerFrame.setSize(new Dimension(350, 400));
		controllerFrame.setLocation(new Point(900, 300));
		controllerFrame.setVisible(true);
		
	} // end of method buildControllerView
	
	static WindowAdapter windowAdapter = new WindowAdapter() {
		
		public void windowClosing(WindowEvent e) {
			
			model.stop();
			System.exit(0);
			
		} // end of method windowClosing
		
		public void windowDeiconified(WindowEvent e) {
			
			model.start();
			
		} // end of method windowDeiconified
		
		public void windowIconified(WindowEvent e) {
			
			model.stop();
			
		} // end of method windowIconified
		
	}; // end of class WindowAdapter
	
} // end of class DigitalClock
