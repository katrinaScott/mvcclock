package src;

public class DTUpdateSecondCmd implements DigitalClockCmd {

	private DigitalClockModel model;
	
	public DTUpdateSecondCmd(DigitalClockModel model) {
		
		this.model = model;
		
	} // end of constructor

	public void Execute() {
		
		model.incrementSecond();

	} // end of method Execute

	public void UnExecute() {
		
		model.decrementSecond();

	} // end of method UnExecute

} // end of class UpdateSecondCmd
