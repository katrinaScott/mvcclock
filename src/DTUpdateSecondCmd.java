package src;

public class DTUpdateSecondCmd implements DigitalClockCmd {

	private DigitalClockModel model;
	private int prevSecond, curSecond;
	
	public DTUpdateSecondCmd(DigitalClockModel model, String seconds) {
		
		this.model = model;
		this.curSecond = Integer.parseInt(seconds);
		
	} // end of constructor

	public void Execute() {
		
		this.prevSecond = model.getSecond();
		model.setSecond(this.curSecond);
		//model.incrementSecond();

	} // end of method Execute

	public void UnExecute() {
		
		model.setSecond(this.prevSecond);
		//model.decrementSecond();

	} // end of method UnExecute

} // end of class UpdateSecondCmd
