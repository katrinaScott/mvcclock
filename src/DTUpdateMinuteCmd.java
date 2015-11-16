package src;

public class DTUpdateMinuteCmd implements DigitalClockCmd {

	private DigitalClockModel model;
	private int prevMin, curMin;
	
	public DTUpdateMinuteCmd(DigitalClockModel model, String minutes) {
		
		this.model = model;
		this.curMin = Integer.parseInt(minutes);
		
	} // end of constructor

	public void Execute() {
		
		this.prevMin = model.getMinute();
		model.setMinute(this.curMin);

	} // end of method Execute

	public void UnExecute() {
		
		model.setMinute(this.prevMin);

	} // end of method UnExecute

} // end of class DTUpdateMinuteCmd
