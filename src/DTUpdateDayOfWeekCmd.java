/**
 * An example command. 
 * Note that to UnExecute the day of the week, we have to know what
 * day to roll it back to.. 
 * Do not be afraid to define whatever instance variables and methods you need here.
 */
class DTUpdateDayOfWeekCmd implements DigitalClockCmd {
	
	/*
	public void Execute() {
		// TBD
	}
	
	public void UnExecute() {
		// TBD
	}*/
	
	private DigitalClockModel model;
	
	public DTUpdateDayOfWeekCmd(DigitalClockModel model) {
		
		this.model = model;
		
	} // end of constructor
	
	public void Execute() {
		
		model.incrementDay();
		
	} // end of method Execute
	
	public void UnExecute() {
		
		model.decrementDay();
		
	} // end of method UnExecute
	
} // end of class DTUpdateDayOfWeekCmd

// And of course, you will define a number of different DigitalClockCmd Objects.