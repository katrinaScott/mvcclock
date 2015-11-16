package src;

public class DTUpdateDayCmd implements DigitalClockCmd {

private DigitalClockModel model;
private int prevDay, curDay;
	
	public DTUpdateDayCmd(DigitalClockModel model, String day) {
		
		this.model = model;
		this.curDay = Integer.parseInt(day);
		
	} // end of constructor
	
	public void Execute() {
		
		this.prevDay = model.getDay();
		model.setDay(this.curDay);
		
	} // end of method Execute
	
	public void UnExecute() {
		
		model.setDay(this.prevDay);
		
	} // end of method UnExecute

} // end of class DTUpdateDayCmd
