package src;

public class DTUpdateSecondCmd implements DigitalClockCmd {

	private DigitalClockModel model;
	private int secondNum;
	
	public DTUpdateSecondCmd(DigitalClockModel model, String seconds) {
		
		this.model = model;
		this.secondNum = Integer.parseInt(seconds);
		
	} // end of constructor

	public void Execute() {
		
		model.setSecond(this.secondNum);
		//model.incrementSecond();

	} // end of method Execute

	public void UnExecute() {
		
		//model.decrementSecond();

	} // end of method UnExecute

} // end of class UpdateSecondCmd
