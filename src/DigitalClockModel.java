package src;

import java.util.Calendar;

/**
 * Digital timer model
 */

class DigitalClockModel implements Runnable {

	private int second;
	private int minute;
	private int hour;
	private int dayOfWeek;
	private int day;
	private int month;
	private int year;
    private Thread thread;
    //private List<DigitalClockView> observers = new ArrayList<DigitalClockView>();
    private DigitalClockController controller;
    
    public String daysOfWeek[] = {
    		" ", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };
    public String months[] = {
    		" ", "January", "February", "March", "April", "May", "June", "July", "August", 
    		"September", "October", "November", "December"
    };
    
	public DigitalClockModel() {

		Calendar local = Calendar.getInstance();
		
		second = local.get(Calendar.SECOND);
		minute = local.get(Calendar.MINUTE);
		hour = local.get(Calendar.HOUR);
		dayOfWeek = local.get(Calendar.DAY_OF_WEEK);
		day = local.get(Calendar.DAY_OF_MONTH);
		month = local.get(Calendar.MONTH);
		year = local.get(Calendar.YEAR);
		
	} // end of constructor
	
	public void start() {
		
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
        
    } // end of method start


    public synchronized void stop() {
    	
        thread = null;
        
    } // end of method stop


    public void run() {
    	
        Thread me = Thread.currentThread();
        while (thread == me) {
        	
        	/*// DD: **Note what is happening here: the model is directly telling
        	// the view to redraw every second. This is *wrong* -- every second,
        	// the model should update its internal state. And whenever there is
        	// an internal state change, the controller must be alerted. Each
        	// controller will then tell all views to update itself accordingly.  
            _dtView.draw();*/
        	
        	incrementSecond();
        	notifyController();
            
            try {
            	
                Thread.sleep(1000);
                
            } catch (InterruptedException e) { break; }
            
        }
        
        thread = null;
        
    } // end of method run
    
    public void notifyController() {
    	
    	controller.notifyObservers();
    	
    } // end of method notifyController
    
//    public void notifyObservers() {
//    	
//    	for (DigitalClockView view : observers) {
//    		
//    		view.draw();
//    		
//    	} // end of for
//    	
//    } // end of method notifyObservers
    
    
    public void incrementSecond() {
    	
        if (second >= 59) {
        	
            second = 0;
            incrementMin();
            
        } else {
        	
            second++;
            
        } // end of if-else
        
    } // end of method incrementSecond
    
    
    public void decrementSecond() {
    	
    	if (second <= 0) {
    		
    		second = 59;
    		decrementMin();
    		
    	} else {
    		
    		second--;
    		
    	} // end of if-else
    	
    } // end of method decrementSecond
    
    public void incrementMin() {
    	
        if (minute >= 59) {
        	
            minute = 0;
            incrementHour();
            
        } else {
        	
            minute++;
            
        } // end of if-else
        
    } // end of method incrementMin
    
    public void decrementMin() {
    	
    	if (minute <= 0) {
    		
    		minute = 59;
    		decrementHour();
    		
    	} else {
    		
    		minute--;
    		
    	} // end of if-else
    	
    } // end of method decrementMin
    
    public void incrementHour() {
    	
        if (hour >= 23) {
        	
            hour = 0;
            incrementDay();
            
        } else {
        	
            hour++;
            
        } // end of if-else
        
    } // end of method incrementHour
    
    public void decrementHour(){
    	
    	if (hour <= 0) {
    		
    		hour = 23;
    		decrementDay();
    		
    	} else {
    		
    		hour--;
    		
    	} // end of if-else
    	
    } // end of method decrementHour
    
    public void incrementDay() {
    	
        if (day >= 28 && month == 2) {
        	
            day = 1;
            incrementMonth();
            
        } else if ((day >= 30) && ((month == 4) || (month == 6) || 
                (month == 9) || (month == 11))) {
        	
            day = 1;
            incrementMonth();
            
        } else if (day >= 31){
        	
            day = 1;
            incrementMonth();
            
        } else {
        	
            day++;
            
        } // end of if-else if-else
        
        incrementDayOfWeek();
        
    } // end of method incrementDay
    
    public void decrementDay() {
    	
        if (day == 1) {
        	
            if (month == 2) {
            	
                day = 28;
                decrementMonth();
                
            } else if ((month == 4) || (month == 6) || (month == 9) ||
                    (month == 11)) {
            	
                day = 30;
                decrementMonth();
                
            } else {
            	
                day = 31;
                decrementMonth();
                
            } // end of if-else if-else
            
        } else {
        	
            day--;
            
        } // end of if-else
        
        decrementDayOfWeek();
        
    } // end of method decrementDay
    
    public void decrementMonth() {
    	
        if (month == 1) {
        	
            month = 12;
            decrementYear();
            
        } else {
        	
            month--;
            
        } // end of if-else
        
    } // end of method decrementMonth
    
    public void decrementYear() {
    	
        year--;
        
    } // end of method decrementYear
    
    public void incrementDayOfWeek() {
    	
        if (dayOfWeek >= 7) {
        	
            dayOfWeek = 1;
            
        } else {
        	
            dayOfWeek++;
            
        } // end of if-else
        
    } // end of method incrementDayOfWeek
    
    public void decrementDayOfWeek() {
    	
        if (dayOfWeek == 1) {
        	
            dayOfWeek = 7;
            
        } else {
        	
            dayOfWeek--;
            
        } // end of if-else
        
    } // end of method decrementDayOfWeek
    
    public void incrementMonth() {
    	
        if (month >= 12) {
        	
            month = 1;
            incrementYear();
            
        } else {
        	
            month++;
            
        } // end of if-else
        
    } // end of method incrementMonth
    
    public void incrementYear() {
    	
        year++;
        
    } // end of method incrementYear
    
    public String dateToString() {
    	
        String date  = daysOfWeek[dayOfWeek] + ", " + months[month+1] + " " + day + ", " + year;
   
        return date;
        
    } // end of method dateToString
    
    public String timeToString() {
    	
        String time  = hour + " : " + minute + " : " + second;
   
        return time;
        
    } // end of method timeToString

//    //add views
//    public void addObserver(DigitalClockView observer) {
//    	
//        observers.add(observer);
//        observer.addObservable(this);
//        
//    } // end of method addObserver
//    
//	public DigitalClockView getObserver(int i) {
//		
//	    return observers.get(i);
//	    
//	} // end of method getObserver
    
    public void setController(DigitalClockController controller) {
    	
    	this.controller = controller;
    	
    } // end of method addController
    
    public DigitalClockController getController() {
    	
    	return controller;
    	
    } // end of method getController
    
    public int getSecond() {
    	
        return second;
        
    } // end of method getSecond
    
    public void setSecond(int second) {
    	
    	this.second = second;
    	
    } // end of method setSecond
    
    public int getMinute() {
    	
        return minute;
        
    } // end of method getMinute
    
    public void setMinute(int minute) {
    	
    	this.minute = minute;
    	
    } // end of method setMinute
    
    public int getHour() {
    	
        return hour;
        
    } // end of method getHour
    
    public void setHour(int hour) {
    	
    	this.hour = hour;
    	
    } // end of method setHour
    
    public int getDay() {
    	
        return day;
        
    } // end of method getDay
    
    public void setDay(int day) {
    	
    	this.day = day;
    	
    } // end of method setDay
    
    public int getDayOfWeek() {
    	
    	return dayOfWeek;
    	
    } // end of method getDayOfWeek
    
    public void setDayOfWeek(int dayOfWeek) {
    	
    	this.dayOfWeek = dayOfWeek;
    	
    } // end of method setDayOfWeek
    
    public int getMonth() {
    	
        return month;
        
    } // end of method getMonth
    
    public void setMonth(int month) {
    	
    	this.month = month;
    	
    } // end of method setMonth
    
    public int getYear() {
    	
        return year;
        
    } // end of method getYear
    
    public void setYear(int year) {
    	
    	this.year = year;
    	
    } // end of method setYear
	
} // end of class DigitalClockModel
