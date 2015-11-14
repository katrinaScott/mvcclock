package src;

public class DTUpdateHourCmd implements DigitalClockCmd {

	private DigitalClockModel model;
	
	public DTUpdateHourCmd(DigitalClockModel model) {
		
		this.model = model;
		
	} // end of constructor

	public void Execute() {
		
		model.incrementHour();

	} // end of method Execute

	public void UnExecute() {
		
		model.decrementHour();

	} // end of method UnExecute

} // end of class DTUpdateHourCmd
