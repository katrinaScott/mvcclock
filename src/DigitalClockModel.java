import java.util.ArrayList;
import java.util.List;

/**
 * Digital timer model
 */

class DigitalClockModel implements Runnable {
	
	// data members
	private int _second;
	private int _minute;
	private int _hour;
	private int _dayOfWeek;
	private int _day;
	private int _month;
	private int _year;
    private Thread _thread;
    private List<DigitalClockView> observers = new ArrayList<DigitalClockView>();
    //DigitalClockView _dtView = null;
    
    public String _daysOfWeek[] = {
    		" ", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };
    public String _months[] = {
    		" ", "January", "February", "March", "April", "May", "June", "July", "August", 
    		"September", "October", "November", "December"
    };
    public String years[] = new String[40];
    
	public DigitalClockModel(DigitalClockView view) {
		
		_second = 0;
		_minute = 0;
		_hour = 0;
		_dayOfWeek = 1;
		_day = 1;
		_month = 1;
		_year = 2000;
		
		addObserver(view);
		generateYears();
		
	} // end of constructor
	
	public void start() {
		
        _thread = new Thread(this);
        _thread.setPriority(Thread.MIN_PRIORITY);
        _thread.start();
        
    } // end of method start


    public synchronized void stop() {
    	
        _thread = null;
        
    } // end of method stop


    public void run() {
    	
        Thread me = Thread.currentThread();
        while (_thread == me) {
        	
        	/*// DD: **Note what is happening here: the model is directly telling
        	// the view to redraw every second. This is *wrong* -- every second,
        	// the model should update its internal state. And whenever there is
        	// an internal state change, the controller must be alerted. Each
        	// controller will then tell all views to update itself accordingly.  
            _dtView.draw();*/
        	
        	notifyObservers();
        	incrementSecond();
            
            try {
            	
                Thread.sleep(1000);
                
            } catch (InterruptedException e) { break; }
            
        }
        
        _thread = null;
        
    } // end of method run
    
    public void notifyObservers() {
    	
    	for (DigitalClockView view : observers) {
    		
    		view.draw();
    		
    	} // end of for
    	
    } // end of method notifyObservers
    
    public void incrementSecond() {
    	
        if (_second >= 59) {
        	
            _second = 0;
            incrementMin();
            
        } else {
        	
            _second++;
            
        } // end of if-else
        
    } // end of method incrementSecond
    
    public void incrementMin() {
    	
        if (_minute >= 59) {
        	
            _minute = 0;
            incrementHour();
            
        } else {
        	
            _minute++;
            
        } // end of if-else
        
    } // end of method incrementMin
    
    public void incrementHour() {
    	
        if (_hour >= 23) {
        	
            _hour = 0;
            incrementDay();
            
        } else {
        	
            _hour++;
            
        } // end of if-else
        
    } // end of method incrementHour
    
    public void incrementDay() {
    	
        if (_day >= 28 && _month == 2) {
        	
            _day = 1;
            incrementMonth();
            
        } else if ((_day >= 30) && ((_month == 4) || (_month == 6) || 
                (_month == 9) || (_month == 11))) {
        	
            _day = 1;
            incrementMonth();
            
        } else if (_day >= 31){
        	
            _day = 1;
            incrementMonth();
            
        } else {
        	
            _day++;
            
        } // end of if-else if-else
        
        incrementDayOfWeek();
        
    } // end of method incrementDay
    
    public void decrementDay() {
    	
        if (_day == 1) {
        	
            if (_month == 2) {
            	
                _day = 28;
                decrementMonth();
                
            } else if ((_month == 4) || (_month == 6) || (_month == 9) ||
                    (_month == 11)) {
            	
                _day = 30;
                decrementMonth();
                
            } else {
            	
                _day = 31;
                decrementMonth();
                
            } // end of if-else if-else
            
        } else {
        	
            _day--;
            
        } // end of if-else
        
        decrementDayOfWeek();
        
    } // end of method decrementDay
    
    public void decrementMonth() {
    	
        if (_month == 1) {
        	
            _month = 12;
            decrementYear();
            
        } else {
        	
            _month--;
            
        } // end of if-else
        
    } // end of method decrementMonth
    
    public void decrementYear() {
    	
        _year--;
        
    } // end of method decrementYear
    
    public void incrementDayOfWeek() {
    	
        if (_dayOfWeek >= 7) {
        	
            _dayOfWeek = 1;
            
        } else {
        	
            _dayOfWeek++;
            
        } // end of if-else
        
    } // end of method incrementDayOfWeek
    
    public void decrementDayOfWeek() {
    	
        if (_dayOfWeek == 1) {
        	
            _dayOfWeek = 7;
            
        } else {
        	
            _dayOfWeek--;
            
        } // end of if-else
        
    } // end of method decrementDayOfWeek
    
    public void incrementMonth() {
    	
        if (_month >= 12) {
        	
            _month = 1;
            incrementYear();
            
        } else {
        	
            _month++;
            
        } // end of if-else
        
    } // end of method incrementMonth
    
    public void incrementYear() {
    	
        _year++;
        
    } // end of method incrementYear
    
    public String toString() {
    	
        //TODO format the zeroes in
        String s  = _hour + ":" + _minute + ":" + _second + "\n" +
        _daysOfWeek[_dayOfWeek] + ", " + _months[_month] + " " + _day + ", " + _year;
        
        return s;
        
    } // end of method toString

    //add views
    public void addObserver(DigitalClockView observer) {
    	
        observers.add(observer);
        observer.addObservable(this);
        
    } // end of method addObserver
    
    public int getSecond() {
    	
        return _second;
        
    } // end of method getSecond
    
    public int getMinute() {
    	
        return _minute;
        
    } // end of method getMinute
    
    public int getHour() {
    	
        return _hour;
        
    } // end of method getHour
    
	public DigitalClockView getObserver(int i) {
		
	    return observers.get(i);
	    
	} // end of method getObserver
    
	private void generateYears() {
		
	    int count = 0;
	    
	    for (int i = 2011; i <= 2050; i++) {
	    	
	        years[count] = Integer.toString(i);
	        count++;
	        
	    } // end of for
	    
	} // end of method generateYears
	
	//for combo boxes
	public String[] generateDaysOfWeek() {
		
	    String[] s = new String[7];
	    
	    for (int i = 0; i < 7; i++) {
	    	
	        s[i] = _daysOfWeek[i + 1];
	        
	    } // end of for
	    
	    return s;
	    
	} // end of method generateDaysOfWeek
	
	public String[] generateMonths() {
		
	    String[] s = new String[12];
	    
	    for (int i = 0; i < 12; i++) {
	    	
	        s[i] = _months[i + 1];
	        
	    } // end of for
	    
	    return s;
	    
	} // end of method generateMonths
	
} // end of class DigitalClockModel
