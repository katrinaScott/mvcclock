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
		
		model = new DigitalClockModel(dialView);
		model.addObserver(digitalView);
		
		buildControllerView(model);
		buildDigitalClockView(digitalView);
		buildDigitalClock3DialsView(model, dialView);
		model.start();
		
	} // end of main method
	
	private static void buildDigitalClockView(DigitalClockView digitalView) {
		
		JFrame digitalFrame = new JFrame("Digital");
		digitalFrame.addWindowListener(windowAdapter);
		digitalFrame.add(digitalView);
		digitalView.add(new JLabel("placeholder time"));
		// add model's string to it
		
		digitalFrame.pack();
		digitalFrame.setSize(new Dimension(400, 300));
		digitalFrame.setLocation(new Point(20, 100));
		digitalFrame.setVisible(true);
		
	} // end of method buildDigitalClockView
	
	private static void buildDigitalClock3DialsView(final DigitalClockModel dtModel, DigitalClockView dialView) {
		
		JFrame dialFrame = new JFrame("3 Dial");
		dialFrame.addWindowListener(windowAdapter);
		dialFrame.getContentPane().add("Center", dialView);
		dialFrame.pack();
		dialFrame.setSize(new Dimension(400, 300));
		dialFrame.setLocationRelativeTo(null);
		
		//dialFrame.add(digitalView);
		//dialView.add(new JLabel("placeholder time"));
		// add model's string to it

		dialFrame.setVisible(true);
	} // end of method buildDigitalClock3DialsView
	
	private static void buildControllerView(DigitalClockModel model) {
		
		JFrame controllerFrame = new DigitalClockController(model);
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
