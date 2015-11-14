package src;

public class DTUpdateMinuteCmd implements DigitalClockCmd {

	private DigitalClockModel model;
	
	public DTUpdateMinuteCmd(DigitalClockModel model) {
		
		this.model = model;
		
	} // end of constructor

	public void Execute() {
		
		model.incrementMin();

	} // end of method Execute

	public void UnExecute() {
		
		model.decrementMin();

	} // end of method UnExecute

} // end of class DTUpdateMinuteCmd
