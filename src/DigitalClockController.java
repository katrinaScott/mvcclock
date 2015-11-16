package src;

import java.util.ArrayList;
import java.util.List;

class DigitalClockController {
	
	private List<DigitalClockView> observers = new ArrayList<DigitalClockView>();
	private CommandStore commandStore = new CommandStore();
	private DigitalClockCmd command;
	DigitalClockModel model;
	
	public DigitalClockController(DigitalClockModel model) {

		this.model = model;
		
		DigitalClockView dialView = new DigitalClock3DialsView();
		DigitalClockView digitalView = new DigitalClockDigitalView();
			
		addObserver(dialView);
		addObserver(digitalView);

		digitalView.buildDigitalClockView();
		dialView.buildDigitalClock3DialsView();
		
	} // end of constructor
	
    public void notifyObservers() {
    	
    	for (DigitalClockView view : observers) {
    		
    		view.updateTime(model.getSecond(), model.getMinute(), model.getHour());
    		view.draw();
    		
    	} // end of for
    	
    } // end of method notifyObservers
	
    //add views
    public void addObserver(DigitalClockView observer) {
    	
        observers.add(observer);
        observer.addObservable(this);
        
    } // end of method addObserver
    
	public DigitalClockView getObserver(int i) {
		
	    return observers.get(i);
	    
	} // end of method getObserver
	
	public void newDigitalView() {
		
		DigitalClockView digital = new DigitalClockDigitalView();
		addObserver(digital);
		digital.buildDigitalClockView();
		
		notifyObservers();
		
	} // end of method newDigitalView
	
	public void newDialView(){
		
		DigitalClockView dial = new DigitalClock3DialsView();
		addObserver(dial);
		dial.buildDigitalClock3DialsView();
		
		notifyObservers();
		
	} // end of method newDialView
	
	public void updateSecond(String second) {
		
		command = new DTUpdateSecondCmd(model, second);
		commandStore.Execute(command);
		
		notifyObservers();
		
	} // end of method updateSecond
	
	public void updateMinute(String minutes) {
		
		command = new DTUpdateMinuteCmd(model, minutes);
		commandStore.Execute(command);
		
		notifyObservers();
		
	} // end of method updateMinute
	
	public void updateHour(String hour) {
		
		command = new DTUpdateHourCmd(model, hour);
		commandStore.Execute(command);
		
		notifyObservers();
		
	} // end of method updateHour
	
	public void updateDay(String day) {
		
		command = new DTUpdateDayCmd(model, day);
		commandStore.Execute(command);
		
		notifyObservers();
		
	} // end of method updateDay
	
	public void updateMonth(String month) {
		
		command = new DTUpdateMonthCmd(model, month);
		commandStore.Execute(command);
		
		notifyObservers();
		
	} // end of method updateMonth
	
	public void updateYear(String year) {
		
		command = new DTUpdateYearCmd(model, year);
		commandStore.Execute(command);
		
		notifyObservers();
		
	} // end of method updateYear
	
	public void undo() {
		
		commandStore.Undo();
		
		notifyObservers();
		
	} // end of method undo
	
	public void redo() {
		
		commandStore.Redo();
		
		notifyObservers();
		
	} // end of method redo
	
} // end of class DigitalClockController
