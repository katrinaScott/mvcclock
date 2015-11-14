package src;

public class DTUpdateMinuteCmd implements DigitalClockCmd {

	private DigitalClockModel model;
	private int minNum;
	
	public DTUpdateMinuteCmd(DigitalClockModel model, String minutes) {
		
		this.model = model;
		this.minNum = Integer.parseInt(minutes);
		
	} // end of constructor

	public void Execute() {
		
		model.setMinute(this.minNum);
		//model.incrementMin();

	} // end of method Execute

	public void UnExecute() {
		
		//model.decrementMin();

	} // end of method UnExecute

} // end of class DTUpdateMinuteCmd
