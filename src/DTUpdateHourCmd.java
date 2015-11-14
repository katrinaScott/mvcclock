package src;

public class DTUpdateHourCmd implements DigitalClockCmd {

	private DigitalClockModel model;
	private int hourNum;
	
	public DTUpdateHourCmd(DigitalClockModel model, String hour) {
		
		this.model = model;
		this.hourNum = Integer.parseInt(hour);
		
	} // end of constructor

	public void Execute() {
		
		model.setHour(this.hourNum);
		//model.incrementHour();

	} // end of method Execute

	public void UnExecute() {
		
		//model.decrementHour();

	} // end of method UnExecute

} // end of class DTUpdateHourCmd
