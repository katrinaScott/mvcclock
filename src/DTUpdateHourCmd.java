package src;

public class DTUpdateHourCmd implements DigitalClockCmd {

	private DigitalClockModel model;
	private int prevHour, curHour;
	
	public DTUpdateHourCmd(DigitalClockModel model, String hour) {
		
		this.model = model;
		this.curHour = Integer.parseInt(hour);
		
	} // end of constructor

	public void Execute() {
		
		this.prevHour = model.getHour();
		model.setHour(this.curHour);
		//model.incrementHour();

	} // end of method Execute

	public void UnExecute() {
		
		model.setHour(this.prevHour);
		//model.decrementHour();

	} // end of method UnExecute

} // end of class DTUpdateHourCmd
